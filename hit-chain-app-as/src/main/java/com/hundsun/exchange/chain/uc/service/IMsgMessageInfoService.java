package com.hundsun.exchange.chain.uc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hundsun.exchange.chain.uc.entity.MsgMessageInfo;

/**
 * 功能说明：信息Service<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public interface IMsgMessageInfoService extends IService<MsgMessageInfo> {

    /**
     * 短信提醒
     */
    void smsWarn();
}
