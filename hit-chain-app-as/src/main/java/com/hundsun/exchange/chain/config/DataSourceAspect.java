package com.hundsun.exchange.chain.config;

import com.hundsun.exchange.chain.annotation.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
//@Component
//@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
//@Aspect

/**
 * 功能说明：数据源逻辑处理<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public class DataSourceAspect {

    @Pointcut("@within(com.hundsun.exchange.chain.annotation.DataSource) || @annotation(com.hundsun.exchange.chain.annotation.DataSource)")
    public void pointCut() {}

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource) {
        log.info("change datasource {}", dataSource.value().getValue());
        DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter() {
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }
}
