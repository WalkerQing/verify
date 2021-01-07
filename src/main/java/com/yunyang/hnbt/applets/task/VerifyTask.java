package com.yunyang.hnbt.applets.task;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyang.hnbt.applets.constant.MerchantType;
import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.yunyang.hnbt.applets.service.IMerchartAmountDetailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Component
public class VerifyTask {

    @Value("${ftp.user}")
    private String user;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.ip}")
    private String ip;
    @Value("${ftp.port}")
    private String port;
    private final static String FILE_NAME = DateUtil.format(DateUtil.offsetDay(DateUtil.date(),-1),"yyyyMMdd");
    private final static String PATH_NAME = "D:\\Tencent\\timFile\\input\\";
    @Autowired
    private Ftp ftp;
    @Autowired
    private IMerchartAmountDetailService service;



    @Bean
    public Ftp initFtp(){
        Ftp ftp = new Ftp(ip,Integer.parseInt(port),user,password);
        return ftp;
    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void verify(){
        Boolean flag = true;
        int num = 0;
        String dateAllStr = FILE_NAME;
        Integer year = Integer.parseInt(FILE_NAME.substring(0,4));
        Integer month = Integer.parseInt(FILE_NAME.substring(5,2));
        Integer day = Integer.parseInt(FILE_NAME.substring(7,2));
        File file = FileUtil.file(PATH_NAME + FILE_NAME);
        if(!file.exists()){
            ftp.download("/input/" + FILE_NAME,file);
        }
        List<String> inputStrs = FileUtil.readLines(file,"UTF-8");
        for(String str : inputStrs){
            if(flag){
                flag = false;
                continue;
            }
            String [] strs = str.split("\\|");
            MerchartAmountDetail merchartAmountDetail = new MerchartAmountDetail(strs[0],dateAllStr,year,month,day, MerchantType.INPUT.getType(), new BigDecimal(strs[2]).doubleValue(),strs[1],LocalDateTime.now());
            QueryWrapper<MerchartAmountDetail> queryWrapper = new QueryWrapper<MerchartAmountDetail>(merchartAmountDetail);
            if(service.count(queryWrapper) != 0){
                continue;
            }
            service.save(merchartAmountDetail);

        }
    }
}
