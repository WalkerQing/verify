package com.yunyang.hnbt.applets.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qy
 * @since 2021-01-06
 */
public interface IMerchartAmountDetailService extends IService<MerchartAmountDetail> {

    Integer DEFAULT_BATCH_SIZE = 10000;

    /**
     * desc 充值
     * @param areaCode
     */
    public List<MerchartAmountDetail> selectTodayInputDetail(String areaCode);

    /**
     * desc 退款
     */
    public List<MerchartAmountDetail> selectTodayRefundDetail(String tenantName);

    /**
     * desc 支出
     */
    public List<MerchartAmountDetail> selectTodayOutputDetail(String tenantName);
   /**
     * desc 充值
     * @param areaCode
     */
    public List<MerchartAmountDetail> selectAllInputDetail(String areaCode);

    /**
     * desc 退款
     */
    public List<MerchartAmountDetail> selectAllRefundDetail(String tenantName);

    /**
     * desc 支出
     */
    public List<MerchartAmountDetail> selectAllOutputDetail(String tenantName);


    public Integer count(QueryWrapper<MerchartAmountDetail> queryWrapper, String tenantName);

    public Integer save(MerchartAmountDetail merchartAmountDetail, String tenantName);
    public Boolean batchSave(List<MerchartAmountDetail> merchartAmountDetails, String tenantName);

    public void insertAllOutput(String tenantName);

    /**
     * 删除
     * @param tenantName
     */
    public void delete(String tenantName);

    /**
     * 删除
     * @param tenantName
     */
    public void deleteAll(String tenantName);


}
