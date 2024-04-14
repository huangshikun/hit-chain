package com.hundsun.exchange.chain.annotation;

import com.hundsun.exchange.chain.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * 功能说明：数据源自定义注解<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.MASTER;
}
