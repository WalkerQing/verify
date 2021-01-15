package com.yunyang.hnbt.applets.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AreaCode {
    NaPo("451026","hnbt_451026"),
    LingYun("451027","hnbt_451027"),
    TianDong("451022","hnbt_451022"),
    JingXi("451081","hnbt_451081");

    private String areaCode;
    private String db;
}
