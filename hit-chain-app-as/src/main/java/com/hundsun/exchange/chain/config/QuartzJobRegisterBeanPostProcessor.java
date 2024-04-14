package com.hundsun.exchange.chain.config;

import com.hundsun.exchange.chain.annotation.QuartzJobRegister;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 功能说明：定时任务自定义注解处理类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Component
@Slf4j
public class QuartzJobRegisterBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private Environment env;

    @SuppressWarnings("unchecked")
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean.getClass().isAnnotationPresent(QuartzJobRegister.class)) {
            String className = bean.getClass().getName();
            log.info("检测到定时任务[{}]", beanName);
            if (bean instanceof Job) {
                try {
                    QuartzJobRegister quartzJobRegister = bean.getClass().getAnnotation(QuartzJobRegister.class);
                    String cornExp = quartzJobRegister.cronExpression();
                    String group = quartzJobRegister.group();
                    String config = quartzJobRegister.config();
                    // 动态从配置文件获取
                    if (StringUtils.isNotBlank(config)) {
                        String dynamicCornExp = env.getProperty(config);
                        if (StringUtils.isNotBlank(dynamicCornExp)) {
                            cornExp = dynamicCornExp;
                        }
                    }

                    // 创建一个job
                    JobDetailImpl jobDetail = new JobDetailImpl();
                    jobDetail.setJobClass((Class<? extends Job>) bean.getClass());
                    jobDetail.setKey(new JobKey(className, group));
                    // job的数据
                    JobDataMap jobDataMap = new JobDataMap();
                    jobDetail.setJobDataMap(jobDataMap);
                    // trigger
                    CronTriggerImpl trigger = new CronTriggerImpl();
                    TriggerKey triggerKey = TriggerKey.triggerKey(className, group);
                    trigger.setKey(triggerKey);
                    trigger.setCronExpression(cornExp);
                    if (scheduler.checkExists(triggerKey)) {
                        scheduler.rescheduleJob(triggerKey, trigger);
                        log.info("更新定时任务[{}]到quartz...OK!", beanName);
                    } else {
                        // 开始一个定时任务
                        scheduler.scheduleJob(jobDetail, trigger);
                        log.info("添加定时任务[{}]到quartz...OK!", beanName);
                    }
                } catch (Exception e) {
                    log.error("添加/更新定时任务失败", e);
                }
            }
        }
        return bean;
    }
}