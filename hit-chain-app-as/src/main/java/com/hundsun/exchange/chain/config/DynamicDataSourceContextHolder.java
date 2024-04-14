package com.hundsun.exchange.chain.config;

import com.hundsun.exchange.chain.enums.DataSourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明：动态数据源上下文处理类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceEnum.MASTER::getValue);

    public static List<Object> dataSourceKeys = new ArrayList<>(DataSourceEnum.values().length);

    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    public static Object getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

//    public static Boolean containDataSourceKey(String key) {
//        return dataSourceKeys.contains(key);
//    }
}
