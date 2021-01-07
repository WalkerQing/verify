package com.yunyang.hnbt.applets.service.impl;

import com.yunyang.hnbt.applets.entity.MerchartAmountDetail;
import com.yunyang.hnbt.applets.mapper.MerchartAmountDetailMapper;
import com.yunyang.hnbt.applets.service.IMerchartAmountDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public void insertTodayInputDetail(String areaCode) {
        this.baseMapper.insertTodayInputDetail(areaCode);
    }

    @Override
    public void insertTodayRefundDetail() {
        this.baseMapper.insertTodayRefundDetail();
    }

    @Override
    public void insertTodayOutputDetail() {
        this.baseMapper.insertTodayOutputDetail();
    }
}
