package com.yunyang.hnbt.applets.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyang.hnbt.applets.entity.MerchartAmountDiary;
import com.yunyang.hnbt.applets.mapper.MerchartAmountDiaryMapper;
import com.yunyang.hnbt.applets.service.IMerchartAmountDiaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qy
 * @since 2021-01-06
 */
@Service
public class MerchartAmountDiaryServiceImpl extends ServiceImpl<MerchartAmountDiaryMapper, MerchartAmountDiary> implements IMerchartAmountDiaryService {

    @DS("#tenantName")
    @Override
    public List<MerchartAmountDiary> diary(String tenantName) {
        return this.baseMapper.diary();
    }

    @DS("#tenantName")
    @Override
    public void delete(String tenantName) {
      this.baseMapper.delete(null);
    }

    @DS("#tenantName")
    @Override
    public int save(String tenantName,MerchartAmountDiary diary) {
        return this.baseMapper.insert(diary);
    }

    @DS("#tenantName")
    @Override
    public int count(String tenantName,Wrapper<MerchartAmountDiary> wrapper) {
        return this.baseMapper.selectCount(wrapper);
    }

    @DS("#tenantName")
    @Override
    public List<MerchartAmountDiary> lastBalance(String tenantName) {
        return this.baseMapper.lastBalance();
    }

    @DS("#tenantName")
    @Override
    public void deleteAll(String tenantName) {
        this.baseMapper.deleteAll();
    }
}
