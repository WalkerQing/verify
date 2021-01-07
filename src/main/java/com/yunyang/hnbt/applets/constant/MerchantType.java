package com.yunyang.hnbt.applets.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MerchantType {
    /**
     * 充值
     */
    INPUT(1),
    /**
     * 支出
     */
    OUTPUT(2),
    /**
     * 退款
     */
    REFUND(3);

    private Integer type;

}
