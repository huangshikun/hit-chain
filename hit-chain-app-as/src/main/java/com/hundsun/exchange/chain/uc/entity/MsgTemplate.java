package com.hundsun.exchange.chain.uc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能说明：信息模板表<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@TableName("MSG_TEMPLATE")
public class MsgTemplate extends Model<MsgTemplate> {

    /**
     * 自增ID
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /** 模板编号 */
    private String code;

    /** 标题 */
    private String subject;

    /**  */
    private BigDecimal client;

    /** 类型 */
    private String type;

    /** 创建日期 */
    private Date gmtCreate;

    /** 修改日期 */
    private Date gmtModify;

    /** 是否已删除 */
    private String isDelete;

    /** 交易ID */
    private BigDecimal exchangeId;

    /**  */
    private BigDecimal provider;

    /**  */
    private String codeAlias;

    /** 模板内容 */
    private String text;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getClient() {
        return client;
    }

    public void setClient(BigDecimal client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public BigDecimal getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(BigDecimal exchangeId) {
        this.exchangeId = exchangeId;
    }

    public BigDecimal getProvider() {
        return provider;
    }

    public void setProvider(BigDecimal provider) {
        this.provider = provider;
    }

    public String getCodeAlias() {
        return codeAlias;
    }

    public void setCodeAlias(String codeAlias) {
        this.codeAlias = codeAlias;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
