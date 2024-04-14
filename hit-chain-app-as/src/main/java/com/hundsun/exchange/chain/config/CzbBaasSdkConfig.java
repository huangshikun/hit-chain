package com.hundsun.exchange.chain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 功能说明：浙商SDK配置类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Configuration
public class CzbBaasSdkConfig {

    @Autowired
    private Environment env;

    /**
     * 初始化客户端
     */
    @Bean
    public CzbBaasSdkClient czbBaasSdkClient() {
        // 默认读取sdkclient.properties
        // SdkClient sdkClient = new SdkClient("xxx.properties"); //读取自定义配置文件xxx.properties
        return new CzbBaasSdkClient(env);
    }
}
