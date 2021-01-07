package com.yunyang.hnbt.applets.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author qy
 * @since 2021-01-06
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MerchartAmountDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 完整的日期
     */
    private String dateallstr;

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
     * 类型1充值2退款3支出
     */
    private Integer type;

    /**
     * 发生额
     */
    private Double actualAmount;

    /**
     * 业务id
     */
    private String businessId;

    private LocalDateTime createTime;


}
