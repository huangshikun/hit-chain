package com.hundsun.exchange.chain.job;

import com.hundsun.exchange.chain.annotation.QuartzJobRegister;
import com.hundsun.exchange.chain.controller.ChainController;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 功能说明：上链自动推送定时任务<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Slf4j
@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@QuartzJobRegister(cronExpression = "0 0/30 * * * ?", group = "chain_group", config = "cron.sms.warn")
public class SmsWarnJob extends QuartzJobBean {

    @Autowired
    private ChainController chainController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("仓储仓单上链异常提醒定时任务开始");
        chainController.smsWarn();
        log.info("仓储仓单上链异常提醒定时任务结束");
    }
}