package com.hundsun.exchange.chain.enums;

/**
 * 功能说明：上链状态枚举类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public enum ChainStatusEnum {

    // 处理成功
    SUCCESS("1", "处理成功"),
    // 处理失败
    FAIL("2", "处理失败");

    ChainStatusEnum(String code, String name) {
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
