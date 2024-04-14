package com.hundsun.exchange.chain.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 功能说明：扩展Spring的AbstractRoutingDataSource抽象类，实现动态数据源。 AbstractRoutingDataSource中的抽象方法determineCurrentLookupKey是实现数据源的route的核心，这里对该方法进行Override。 【上下文DbContextHolder为一线程安全的ThreadLocal】<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("current datasource is {}", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
