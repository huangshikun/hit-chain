package com.hundsun.exchange.chain.enums;

/**
 * 功能说明：上链类型枚举类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public enum ChainTypeEnum {

    // 库存上链
    INVENTORY("1", "库存上链"),
    // 仓单上链
    RECEIPT("2", "仓单上链");

    ChainTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
