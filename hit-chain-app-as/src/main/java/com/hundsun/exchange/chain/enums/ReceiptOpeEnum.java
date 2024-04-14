package com.hundsun.exchange.chain.enums;

/**
 * 功能说明：仓单操作枚举类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public enum ReceiptOpeEnum {

    // 冻结
    FROZEN("1", "冻结"),
    FREE("2", "解冻"),
    TRANSFER("4", "过户"),
    SUB("5", "过户减少"),
    ADD("6", "过户增加"),
    SPLIT("8", "拆分"),
    WEIGHT_CHANGE("11", "重量变更"),
    QUALITY_DATE_CHANGE("27", "质检日期变更"),
    OUT("12", "出库"),
    LOCATION_CHANGE("13", "货位变更"),
    JOIN("16", "合并"),
    PLEDGE("32", "质押"),
    PLEDGE_NOT("64", "解质押"),
    PLEDGE_REVERSE("65", "质押冲正"),
    MORTGAGE("128", "抵押"),
    MORTGAGE_NOT("256", "解抵押"),
    PLEDGE_NOT_RESET("512", "解质押回滚"),
    CANCEL("0", "注销"),
    NORMAL("3", "恢复正常"),
    REGISTER("14", "注册");

    private final String code;

    private final String name;

    ReceiptOpeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(String code) {
        for (ReceiptOpeEnum item : ReceiptOpeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item.getName();
            }
        }
        return code;
    }
}
