package com.hundsun.exchange.chain.enums;

/**
 * 功能说明：多数据源枚举类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public enum DataSourceEnum {

    // 主数据源（默认）
    MASTER("master"),
    // 从数据源
    SLAVE("slave"),
    // UC数据源
    UC("uc");

    private final String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
