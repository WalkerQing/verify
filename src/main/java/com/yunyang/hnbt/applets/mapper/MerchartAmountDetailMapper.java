package com.yunyang.hnbt.applets.mapper;

import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qy
 * @since 2021-01-06
 */
public interface MerchartAmountDetailMapper extends BaseMapper<MerchartAmountDetail> {
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
