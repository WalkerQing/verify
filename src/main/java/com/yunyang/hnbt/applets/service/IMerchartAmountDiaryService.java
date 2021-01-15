package com.yunyang.hnbt.applets.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yunyang.hnbt.applets.entity.MerchartAmountDiary;
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
public interface IMerchartAmountDiaryService extends IService<MerchartAmountDiary> {
    /**
     * 统计今日
     * @param tenantName
     * @return
     */
    public List<MerchartAmountDiary> diary(String tenantName);

    /**
     * 删除
     * @param tenantName
     */
    public void delete(String tenantName);

    /**
     * 保存
     * @param tenantName
     * @param diary
     * @return
     */
    public int save(String tenantName,MerchartAmountDiary diary);

    /**
     * count
     * @param tenantName
     * @param wrapper
     * @return
     */
    public int count(String tenantName, Wrapper<MerchartAmountDiary> wrapper);

    /**
     * 最后一次余额
     * @param tenantName
     * @return
     */
    public List<MerchartAmountDiary> lastBalance(String tenantName);

    /**
     * 删除
     * @param tenantName
     */
    public void deleteAll(String tenantName);
}
