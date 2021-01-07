package com.yunyang.hnbt.applets.service;

import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qy
 * @since 2021-01-06
 */
public interface IMerchartAmountDetailService extends IService<MerchartAmountDetail> {
    /**
     * desc 充值
     * @param areaCode
     */
    public void insertTodayInputDetail(String areaCode);

    /**
     * desc 退款
     */
    public void insertTodayRefundDetail();

    /**
     * desc 支出
     */
    public void insertTodayOutputDetail();
}
