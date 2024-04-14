package com.hundsun.exchange.chain.config;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 功能说明：定时任务配置类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Configuration
public class SchedulerConfig {

    @Resource
    private SpringJobFactory springJobFactory;

    @Resource
    private DataSource master;

    @Value("${chain.quartz.instance.name}")
    private String instance;

    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        // 这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
        factory.setWaitForJobsToCompleteOnShutdown(true);
        factory.setJobFactory(springJobFactory);
        // 延长10s启动
        factory.setStartupDelay(10);
        factory.setAutoStartup(true);
        factory.setDataSource(master);
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        factory.setOverwriteExistingJobs(true);
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        // 在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        Properties properties = propertiesFactoryBean.getObject();
        if (StringUtils.isNotBlank(instance)) {
            properties.setProperty("org.quartz.scheduler.instanceName", instance);
        }
        return properties;
    }

    /**
     * quartz初始化监听器 这个监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行。
     * @return
     */
//    @Bean
//    public QuartzInitializerListener executorListener() {
//        return new QuartzInitializerListener();
//    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }
}
