package com.hundsun.exchange.chain.uc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hundsun.exchange.chain.uc.entity.MsgTemplate;

/**
 * 功能说明：信息模板Service<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public interface IMsgTemplateService extends IService<MsgTemplate> {

    /**
     * 获取短信提醒模板
     * @return
     */
    MsgTemplate getByCode();
}
