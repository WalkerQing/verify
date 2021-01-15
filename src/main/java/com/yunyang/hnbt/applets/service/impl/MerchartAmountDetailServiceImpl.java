package com.yunyang.hnbt.applets.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.yunyang.hnbt.applets.mapper.MerchartAmountDetailMapper;
import com.yunyang.hnbt.applets.service.IMerchartAmountDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yunyang.hnbt.applets.service.IMerchartAmountDetailService.DEFAULT_BATCH_SIZE;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qy
 * @since 2021-01-06
 */

@Service
public class MerchartAmountDetailServiceImpl extends ServiceImpl<MerchartAmountDetailMapper, MerchartAmountDetail> implements IMerchartAmountDetailService {




    @Override
    public List<MerchartAmountDetail> selectTodayInputDetail(String areaCode) {
        return this.baseMapper.selectTodayInputDetail(areaCode);
    }

    @DS("#tenantName")
    @Override
    public List<MerchartAmountDetail> selectTodayRefundDetail(String tenantName) {
        return this.baseMapper.selectTodayRefundDetail();
    }

    @DS("#tenantName")
    @Override
    public List<MerchartAmountDetail> selectTodayOutputDetail(String tenantName) {
        return this.baseMapper.selectTodayOutputDetail();
    }

    @DS("#tenantName")
    @Override
    public List<MerchartAmountDetail> selectAllInputDetail(String areaCode) {
        return this.baseMapper.selectAllInputDetail(areaCode);
    }

    @DS("#tenantName")
    @Override
    public List<MerchartAmountDetail> selectAllRefundDetail(String tenantName) {
        return this.baseMapper.selectAllRefundDetail();
    }

    @DS("#tenantName")
    @Override
    public List<MerchartAmountDetail> selectAllOutputDetail(String tenantName) {
        return this.baseMapper.selectAllOutputDetail();
    }

    @DS("#tenantName")
    @Override
    public Integer count(QueryWrapper<MerchartAmountDetail> queryWrapper,String tenantName) {
        return this.baseMapper.selectCount(queryWrapper);
    }


    @DS("#tenantName")
    @Override
    public Integer save(MerchartAmountDetail merchartAmountDetail, String tenantName) {
        return this.baseMapper.insert(merchartAmountDetail);
    }

    @DS("#tenantName")
    @Override
    public Boolean batchSave(List<MerchartAmountDetail> merchartAmountDetails, String tenantName){
        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
        return this.executeBatch(merchartAmountDetails, IMerchartAmountDetailService.DEFAULT_BATCH_SIZE, (sqlSession, entity) -> {
            sqlSession.insert(sqlStatement, entity);
        });
    }

    @DS("#tenantName")
    @Override
    public void insertAllOutput(String tenantName) {
        this.baseMapper.insertAllOutput();
    }

    @DS("#tenantName")
    @Override
    public void delete(String tenantName) {
        this.baseMapper.delete(null);
    }

    @DS("#tenantName")
    @Override
    public void deleteAll(String tenantName) {
        this.baseMapper.deleteAll();
    }


}
