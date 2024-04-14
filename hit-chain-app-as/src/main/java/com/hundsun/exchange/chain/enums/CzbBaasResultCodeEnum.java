package com.hundsun.exchange.chain.enums;

/**
 * 功能说明：上链响应code枚举类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public enum CzbBaasResultCodeEnum {

    // 成功
    SUCCESS("000000", "成功"),

    // 异常
    EXCEPTION("-1", "异常"),

    // 已存在
    EXISTS("B00003", "已存在");

    CzbBaasResultCodeEnum(String code, String name) {
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

    public static boolean checkResult(String code) {
        if (SUCCESS.getCode().equals(code) || EXISTS.getCode().equals(code)) {
            return true;
        } else {
            return false;
        }
    }
}
