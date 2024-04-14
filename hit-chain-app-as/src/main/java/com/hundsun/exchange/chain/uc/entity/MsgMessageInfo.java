package com.hundsun.exchange.chain.uc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;

/**
 * 功能说明：信息表<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@TableName("MSG_MESSAGE_INFO")
@KeySequence(value = "SEQ_MSG_MESSAGE_INFO")
public class MsgMessageInfo extends Model<MsgMessageInfo> {

    /**
     * 自增ID
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /** 发送方式 */
    private String sendWay;

    /** 标题 */
    private String subject;

    /** 模板 */
    private String template;

    /** 内容 */
    private String body;

    /** 收件人 */
    private String destinate;

    /** 发送时间 */
    private BigDecimal sendTime;

    /** 创建时间 */
    private BigDecimal gmtCreate;

    /** 修改时间 */
    private BigDecimal gmtModify;

    /** 是否已经发送 */
    private String isSend;

    /** 发送人名称 */
    private String operateName;

    /** 发送人ID */
    private String operateId;

    /** 状态 */
    private String status;

    /** 业务类型 */
    private String type;

    /** 交易ID */
    private BigDecimal exchangeId;

    /** 内容 */
    private String content;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSendWay() {
        return sendWay;
    }

    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDestinate() {
        return destinate;
    }

    public void setDestinate(String destinate) {
        this.destinate = destinate;
    }

    public BigDecimal getSendTime() {
        return sendTime;
    }

    public void setSendTime(BigDecimal sendTime) {
        this.sendTime = sendTime;
    }

    public BigDecimal getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(BigDecimal gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public BigDecimal getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(BigDecimal gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(BigDecimal exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
