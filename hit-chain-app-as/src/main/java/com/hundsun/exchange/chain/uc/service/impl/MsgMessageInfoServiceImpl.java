package com.hundsun.exchange.chain.uc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.exchange.chain.uc.entity.MsgMessageInfo;
import com.hundsun.exchange.chain.uc.entity.MsgTemplate;
import com.hundsun.exchange.chain.uc.mapper.MsgMessageInfoDao;
import com.hundsun.exchange.chain.uc.service.IMsgMessageInfoService;
import com.hundsun.exchange.chain.uc.service.IMsgTemplateService;
import com.hundsun.exchange.chain.util.DateUtil;
import com.hundsun.exchange.chain.wh.mapper.WhChainInventoryBatchDao;
import com.hundsun.exchange.chain.ws.mapper.WsChainReceiptBatchDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 功能说明：信息Impl<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Service
@Slf4j
public class MsgMessageInfoServiceImpl extends ServiceImpl<MsgMessageInfoDao, MsgMessageInfo> implements IMsgMessageInfoService {

    @Autowired
    private WhChainInventoryBatchDao whChainInventoryBatchDao;

    @Autowired
    private WsChainReceiptBatchDao wsChainReceiptBatchDao;

    @Autowired
    private IMsgTemplateService msgTemplateService;

    @Value("${sms.accept.mobile:}")
    private String mobile;

    @Value("${sms.warn.times:2}")
    private int warnTimes;

    @Value("${chain.push.times:10}")
    private int pushTimes;

    @Override
    public void smsWarn() {
        MsgTemplate msgTemplate = msgTemplateService.getByCode();
        if (null == msgTemplate) {
            log.warn("仓储仓单上链异常短信提醒模板不存在，无法提醒！");
            return;
        }

        if (StringUtils.isBlank(mobile)) {
            log.warn("仓储仓单上链异常短信提醒手机号为空，无法提醒！");
            return;
        }

        if (wsChainReceiptBatchDao.selectWarnBatch(pushTimes, warnTimes) > 0 || whChainInventoryBatchDao.selectWarnBatch(pushTimes, warnTimes) > 0) {
            try {
                MsgMessageInfo msgMessageInfo = new MsgMessageInfo();
                msgMessageInfo.setSubject(msgTemplate.getSubject());
                msgMessageInfo.setTemplate(msgTemplate.getCode());
                msgMessageInfo.setDestinate(mobile);
                msgMessageInfo.setOperateId("-1");
                msgMessageInfo.setOperateName("交易平台");
                msgMessageInfo.setSendTime(DateUtil.getDateBigDecimal(new Date()));
                msgMessageInfo.setGmtCreate(msgMessageInfo.getSendTime());
                msgMessageInfo.setGmtModify(msgMessageInfo.getSendTime());
                msgMessageInfo.setIsSend("N");
                msgMessageInfo.setType("sms");
                msgMessageInfo.setSendWay("save");
                msgMessageInfo.setContent(msgTemplate.getText());
                this.save(msgMessageInfo);
                wsChainReceiptBatchDao.updateWarnTimes(pushTimes, warnTimes);
                whChainInventoryBatchDao.updateWarnTimes(pushTimes, warnTimes);
            } catch (Exception e) {
                log.error("仓储仓单上链异常短信提醒失败", e);
            }
        }
    }
}
