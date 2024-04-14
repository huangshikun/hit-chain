package com.hundsun.exchange.chain.config;

import com.hundsun.exchange.chain.enums.DataSourceEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 功能说明：默认按包路径设置数据源<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Aspect
@Order(1)
@Component
public class DataSourceSwitch {

    @Before("execution(* com.hundsun.exchange.chain.wh.mapper.*Dao.*(..))")
    /** @Before("execution(* com.hundsun.exchange.chain.wh.*.*Service*.*(..))") */
    public void wh(JoinPoint joinPoint) {
        setDataSourceKey(DataSourceEnum.MASTER.getValue());
    }

    @Before("execution(* com.hundsun.exchange.chain.ws.mapper.*Dao.*(..))")
    /** @Before("execution(* com.hundsun.exchange.chain.ws.*.*Service*.*(..))") */
    public void ws(JoinPoint joinPoint) {
        setDataSourceKey(DataSourceEnum.SLAVE.getValue());
    }

    @Before("execution(* com.hundsun.exchange.chain.uc.mapper.*Dao.*(..))")
    public void uc(JoinPoint joinPoint) {
        setDataSourceKey(DataSourceEnum.UC.getValue());
    }

    /**
     * 设置数据源key
     */
    private void setDataSourceKey(final String defaultKey) {
        DynamicDataSourceContextHolder.setDataSourceKey(defaultKey);
    }
}
