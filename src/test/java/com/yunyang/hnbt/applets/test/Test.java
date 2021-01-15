package com.yunyang.hnbt.applets.test;

import cn.hutool.core.date.DateUtil;
import com.yunyang.hnbt.applets.constant.HolidayUtil;
import com.yunyang.hnbt.applets.constant.Week;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //System.out.println(HolidayUtil.getHoliday("20210104"));
        String result = "";

        HolidayUtil.getWorkDay("20210115");
        System.out.println(HolidayUtil.getWorkDay());
        System.out.println(HolidayUtil.isHoliday("20210101"));
        System.out.println(HolidayUtil.isHoliday("20210104"));




    }

    public static String getWorkDay(String str){

        if(HolidayUtil.isHoliday(str)){
            str = DateUtil.format(DateUtil.offsetDay(DateUtil.parse(str),1),"yyyyMMdd");
            getWorkDay(str);
        }
        return str;
    }

}
