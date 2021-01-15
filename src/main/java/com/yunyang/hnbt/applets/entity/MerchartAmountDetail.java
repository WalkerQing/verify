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
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MerchartAmountDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    private final String merNo;

    /**
     * 完整的日期
     */
    @Setter
    private String dateallstr;

    /**
     * 年
     */
    private final Integer yearint;

    /**
     * 月
     */
    @Setter
    private  Integer monthint;

    /**
     * 日
     */
    @Setter
    private  Integer dateint;

    /**
     * 类型1充值2退款3支出
     */
    private final Integer type;

    /**
     * 发生额
     */
    private final Double actualAmount;

    /**
     * 业务id
     */
    private final String businessId;

    @Setter
    private LocalDateTime createTime;


}
