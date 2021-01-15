package com.yunyang.hnbt.applets.mapper;

import com.yunyang.hnbt.applets.entity.MerchartAmountDiary;
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
public interface MerchartAmountDiaryMapper extends BaseMapper<MerchartAmountDiary> {
    /**
     * 统计
     */
    public List<MerchartAmountDiary> diary();

    public List<MerchartAmountDiary> lastBalance();

    public void deleteAll();
}
