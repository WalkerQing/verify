package com.yunyang.hnbt.applets.constant;

import cn.hutool.core.date.DateUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  Week {
    /**
     * 周一
     */
    Monday(2,true),
    /**
     * 周二
     */
    Tuesday(3,true),
    /**
     * 周三
     */
    Wednesday(4,true),
    /**
     * 周四
     */
    Thursday(5,true),
    /**
     * 周五
     */
    Friday(6,true),
    /**
     * 周六
     */
    Saturday(7,false),
    /**
     * 周末
     */
    Sunday(1,false),
    /**
     * other
     */
    Nothing(-1,true);

    /**
     * 天
     */
    private final Integer dayNum;

    /**
     * 是否是工作日
     */
    private final boolean isWorkDay;

    /**
     *
     * @param day
     * @return 是否是工作日
     */
    public static Week get(String day){
        Integer num = DateUtil.dayOfWeek(DateUtil.parse(day));
        for(Week week: Week.values()){
            if(week.getDayNum().equals(num)){
                return week;
            }
        }
        return Nothing;
    }
}
