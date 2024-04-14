package com.hundsun.exchange.chain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置类
 * @author : huangsk20406
 * @date : 2021/8/9
 */
@Configuration
@MapperScan({"com.hundsun.exchange.chain.wh.mapper", "com.hundsun.exchange.chain.ws.mapper", "com.hundsun.exchange.chain.uc.mapper"})
public class MybatisPlusConfig {
}
