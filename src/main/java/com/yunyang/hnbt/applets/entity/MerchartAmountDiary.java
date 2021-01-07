package com.yunyang.hnbt.applets.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qy
 * @since 2021-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MerchartAmountDiary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否已生效1是2否
     */
    private Integer isUse;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 完整日期
     */
    private String datestr;

    /**
     * 年
     */
    private Integer yearint;

    /**
     * 月
     */
    private Integer monthint;

    /**
     * 日
     */
    private Integer dateint;

    /**
     * 支出
     */
    private Double output;

    /**
     * 收入
     */
    private Double input;

    /**
     * 日结果余额（T+1日半夜2点的余额）
     */
    private Double balance;

    private LocalDateTime createTime;

    /**
     * 退款
     */
    private Double refund;


}
