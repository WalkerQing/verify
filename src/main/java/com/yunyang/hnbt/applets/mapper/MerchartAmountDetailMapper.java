package com.yunyang.hnbt.applets.mapper;

import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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
    public List<MerchartAmountDetail> selectTodayInputDetail(String areaCode);

    /**
     * desc 退款
     */
    public List<MerchartAmountDetail> selectTodayRefundDetail();

    /**
     * desc 支出
     */
    public List<MerchartAmountDetail> selectTodayOutputDetail();
    /**
     * desc 充值
     * @param areaCode
     */
    public List<MerchartAmountDetail> selectAllInputDetail(String areaCode);

    /**
     * desc 退款
     */
    public List<MerchartAmountDetail> selectAllRefundDetail();

    /**
     * desc 支出
     */
    public List<MerchartAmountDetail> selectAllOutputDetail();

    /**
     *
     */
    public void insertAllOutput();

    public void deleteAll();
}
