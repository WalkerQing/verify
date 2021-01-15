package com.yunyang.hnbt.applets.task;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.extra.ftp.Ftp;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyang.hnbt.applets.constant.AreaCode;
import com.yunyang.hnbt.applets.constant.HolidayUtil;
import com.yunyang.hnbt.applets.constant.MerchantType;
import com.yunyang.hnbt.applets.constant.Week;
import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.yunyang.hnbt.applets.entity.MerchartAmountDiary;
import com.yunyang.hnbt.applets.service.IMerchartAmountDetailService;
import com.yunyang.hnbt.applets.service.IMerchartAmountDiaryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Log4j2
@Component
public class VerifyTask {


    private final static String FILE_NAME = DateUtil.format(DateUtil.offsetDay(DateUtil.date(),-1),"yyyyMMdd");
    private final static String PATH_NAME = "/home/ftpuser/input/";

    @Autowired
    private IMerchartAmountDetailService service;
    @Autowired
    private IMerchartAmountDiaryService diaryService;

    /**
     * 昨日收支统计
     */
    @Scheduled(cron = "0 0 11,20 * * ? ")
    public void verify(){
        boolean flag = true;
        String unionMerNo = "898450189990243";
        Integer year = Integer.parseInt(FILE_NAME.substring(0,4));
        Integer month = Integer.parseInt(FILE_NAME.substring(4,6));
        Integer day = Integer.parseInt(FILE_NAME.substring(6,8));
        File file = FileUtil.file(PATH_NAME + FILE_NAME);
        if(file.exists()){
            List<String> inputStrs = FileUtil.readLines(file,"UTF-8");
            for(String str : inputStrs){
                if(flag){
                    flag = false;
                    continue;
                }
                String [] strs = str.split("\\|");
                MerchartAmountDetail merchartAmountDetail = new MerchartAmountDetail(strs[0],year,MerchantType.INPUT.getType(), new BigDecimal(strs[2]).doubleValue(),strs[1]);
                merchartAmountDetail.setDateallstr(FILE_NAME);
                merchartAmountDetail.setMonthint(month);
                merchartAmountDetail.setDateint(day);
                QueryWrapper<MerchartAmountDetail> queryWrapper = new QueryWrapper<MerchartAmountDetail>(merchartAmountDetail);
                if(service.count(queryWrapper) != 0){
                    continue;
                }
                merchartAmountDetail.setCreateTime(LocalDateTime.now());
                service.save(merchartAmountDetail);

            }
        }


        for (AreaCode areaCode : AreaCode.values()){
            log.info("开始处理{} 充值记录",areaCode.getAreaCode());
            String areacode = areaCode.getAreaCode();
            List<MerchartAmountDetail> inputList = service.selectTodayInputDetail(areacode);
            List<MerchartAmountDetail> refundSaveList = new ArrayList<>();
            saveMerchantAmountDetail(inputList,areaCode.getDb());

            log.info("开始处理{} 支出记录",areaCode.getAreaCode());
            List<MerchartAmountDetail> outputList = service.selectTodayOutputDetail(areaCode.getDb());
            saveMerchantAmountDetail(outputList,areaCode.getDb());

            log.info("开始处理{} 退款记录",areaCode.getAreaCode());
            List<MerchartAmountDetail> refundList = service.selectTodayRefundDetail(areaCode.getDb());
            refundList.forEach(refund -> {
                String dateStr = refund.getDateallstr();
                if(HolidayUtil.isHoliday(dateStr)){
                    HolidayUtil.getWorkDay(dateStr);
                    String refundDate = HolidayUtil.getWorkDay();
                    refund.setDateallstr(refundDate);
                    refund.setDateint(Integer.parseInt(refundDate.substring(6,8)));
                    refund.setMonthint(Integer.parseInt(refundDate.substring(4,6)));
                }
                refundSaveList.add(refund);

            });
            saveMerchantAmountDetail(refundSaveList,areaCode.getDb());

            log.info("开始处理{} 统计",areaCode.getAreaCode());

            List<MerchartAmountDiary> diaryList = diaryService.diary(areaCode.getDb());
            if(diaryList.isEmpty()){
                continue;
            }
            diaryList.forEach(x->{
                List<MerchartAmountDiary> lastBalanceList = diaryService.lastBalance(areaCode.getDb());
                Map<String,Object> balanceMap = lastBalanceList.stream()
                        .collect(Collectors.toMap(MerchartAmountDiary::getMerNo,MerchartAmountDiary::getBalance));
                if(unionMerNo.equals(x.getMerNo())){
                    return;
                }
                log.info("{} : {}",areaCode.getAreaCode(),x.getMerNo());
                BigDecimal lastBalance = Convert.toBigDecimal(balanceMap.getOrDefault(x.getMerNo(), 0));
                BigDecimal balance = lastBalance.add(BigDecimal.valueOf(x.getInput())).add(BigDecimal.valueOf(x.getRefund()))
                        .subtract(BigDecimal.valueOf(x.getOutput()));
                x.setBalance(balance.doubleValue());
                MerchartAmountDiary diary = new MerchartAmountDiary();
                BeanUtil.copyProperties(x,diary,"balance","input","output","createTime","refund");
                Wrapper<MerchartAmountDiary> wrapper = new QueryWrapper<>(diary);
                if(diaryService.count(areaCode.getDb(),wrapper) > 0){
                    log.info("跳过: {}",x.toString());
                }else{
                    x.setCreateTime(LocalDateTime.now());
                    diaryService.save(areaCode.getDb(),x);
                    log.info("add: {}",x.toString());
                }
            });

        }

    }

    public void verifyAll(){
        String unionMerNo = "898450189990243";
        List<MerchartAmountDetail> refundSaveList = new ArrayList<>();


        for (AreaCode areaCode : AreaCode.values()){
            service.deleteAll(areaCode.getDb());
            diaryService.deleteAll(areaCode.getDb());
            log.info("开始处理{} 充值记录",areaCode.getAreaCode());
            String areacode = areaCode.getAreaCode();
            List<MerchartAmountDetail> inputList = service.selectAllInputDetail(areacode);
            saveMerchantAmountDetail(inputList,areaCode.getDb());

            log.info("开始处理{} 支出记录",areaCode.getAreaCode());
            service.insertAllOutput(areaCode.getDb());
            log.info("处理{} 支出记录完毕",areaCode.getAreaCode());

            log.info("开始处理{} 退款记录",areaCode.getAreaCode());
            List<MerchartAmountDetail> refundList = service.selectAllRefundDetail(areaCode.getDb());
            refundList.forEach(refund ->{
                String dateStr = refund.getDateallstr();
                if(refundSaveList.size() == service.DEFAULT_BATCH_SIZE){
                    service.batchSave(refundSaveList,areaCode.getDb());
                    refundSaveList.clear();
                }
                if(HolidayUtil.isHoliday(dateStr)){
                    HolidayUtil.getWorkDay(dateStr);
                    String refundDate = HolidayUtil.getWorkDay();
                    refund.setDateallstr(refundDate);
                    refund.setDateint(Integer.parseInt(refundDate.substring(6,8)));
                    refund.setMonthint(Integer.parseInt(refundDate.substring(4,6)));
                }
                refundSaveList.add(refund);
            });
            if(refundSaveList.size() >= 1){
                service.batchSave(refundSaveList,areaCode.getDb());
            }
            log.info("处理{} 退款记录完毕",areaCode.getAreaCode());

            log.info("开始处理{} 统计",areaCode.getAreaCode());

            List<MerchartAmountDiary> diaryList = diaryService.diary(areaCode.getDb());
            if(diaryList.isEmpty()){
                continue;
            }
            diaryList.stream().forEach(x->{
                List<MerchartAmountDiary> lastBalanceList = diaryService.lastBalance(areaCode.getDb());
                Map<String,Object> balanceMap = lastBalanceList.stream()
                        .collect(Collectors.toMap(MerchartAmountDiary::getMerNo,MerchartAmountDiary::getBalance));
                if(unionMerNo.equals(x.getMerNo())){
                    return;
                }
                log.info("{} : {}",areaCode.getAreaCode(),x.getMerNo());
                BigDecimal lastBalance = Convert.toBigDecimal(balanceMap.getOrDefault(x.getMerNo(), 0));
                BigDecimal balance = lastBalance.add(BigDecimal.valueOf(x.getInput())).add(BigDecimal.valueOf(x.getRefund()))
                        .subtract(BigDecimal.valueOf(x.getOutput()));
                x.setBalance(balance.doubleValue());
                MerchartAmountDiary diary = new MerchartAmountDiary();
                BeanUtil.copyProperties(x,diary,"balance","input","output","createTime","refund");
                Wrapper<MerchartAmountDiary> wrapper = new QueryWrapper<>(diary);
                if(diaryService.count(areaCode.getDb(),wrapper) > 0){
                    log.info("跳过: {}",x.toString());
                    return;
                }
                x.setCreateTime(LocalDateTime.now());
                diaryService.save(areaCode.getDb(),x);
                log.info("add: {}",x.toString());

            });

        }
    }


    public void saveMerchantAmountDetail(List<MerchartAmountDetail> list,String areaCode){
        AtomicInteger insertNum = new AtomicInteger();
        AtomicInteger skip = new AtomicInteger();
        list.forEach(x->{
            x.setCreateTime(null);
            QueryWrapper<MerchartAmountDetail> queryWrapper = new QueryWrapper<>(x);
            if(service.count(queryWrapper,areaCode)>0){
                skip.getAndIncrement();
                log.info("已存在: {}" , x.toString());
            }else{
                x.setCreateTime(LocalDateTime.now());
                service.save(x,areaCode);
                insertNum.getAndIncrement();
            }
        });
        log.info("{}: 新增 {} 条,已存在 {} 条",areaCode,insertNum,skip);
    }
}
