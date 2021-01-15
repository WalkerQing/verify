package com.yunyang.hnbt.applets.constant;


import cn.hutool.core.date.DateUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class HolidayUtil {

    private static Map<String, Boolean> holidayMap = new HashMap<>();
    @Getter
    private static String workDay = null;

    static {
        String str = "20210101,20210102,20210103,20210211,20210212,20210213,20210214,20210215,20210216,20210217,20210403,20210404,20210405,20210501,20210502,20210503,20210504,20210505,20210612,20210613,20210614,20210919,20210920,20210921,20211001,20211002,20211003,20211004,20211005,20211006,20211007";

        List<String> dateList = Arrays.asList(str.split(","));
        dateList.stream().forEach(x -> {
            holidayMap.put(x, true);
        });
    }

    /**
     * 判断是否是工作日
     * @param dateStr
     * @return
     */
    public static Boolean isHoliday(String dateStr) {
        return holidayMap.getOrDefault(dateStr, false) || !Week.get(dateStr).isWorkDay();
    }

    /**
     * 获取下一个工作日
     * @param dateStr
     */
    public static void getWorkDay(String dateStr){
        workDay = dateStr;
        if(isHoliday(dateStr)){
            workDay = DateUtil.format(DateUtil.offsetDay(DateUtil.parse(dateStr),1),"yyyyMMdd");
            getWorkDay(workDay);
        }
    }

}
