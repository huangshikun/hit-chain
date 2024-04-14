package com.hundsun.exchange.chain.enums;

/**
 * 功能说明：库存操作枚举类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public enum InventoryChangeEnum {

    // 入库
    STOCK_IN("1", "入库"),
    MORTGAGE("5", "签发仓单"),
    STOCK_OUT_FROZEN("7", "出库申请冻结"),
    STOCK_OUT_THAW("8", "出库申请解冻"),
    STOCK_OUT("13", "出库"),
    WEIGHT_CHANGE("15", "重量变更"),
    LOCATION_CHANGE("16", "货位变更"),
    TFS_IN("17", "过户入库"),
    TFS_OUT("18", "过户出库"),
    MOVE_IN("19", "移库入库"),
    MOVE_OUT("20", "移库出库"),
    TRANSFER_APPLY_OUT_FROZEN("21", "过户申请冻结"),
    TRANSFER_APPLY_OUT_FREE("22", "过户申请解冻"),
    RECEIPT_TFS_OUT("23", "仓单过户出库"),
    RECEIPT_TFS_IN("24", "仓单过户入库"),
    INV_FROM_RECEIPT("48", "仓单转库存"),
    WEIGHT_CHANGE_FROM_RECEIPT("39","仓单重量变更");

    InventoryChangeEnum(String code, String name) {
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

    public static String getNameByValue(String code) {
        for (InventoryChangeEnum item : InventoryChangeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item.getName();
            }
        }
        return code;
    }
}
