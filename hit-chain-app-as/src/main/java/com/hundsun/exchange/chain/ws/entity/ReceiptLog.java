package com.hundsun.exchange.chain.ws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能说明：仓单日志表<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@TableName("WH_RECEIPT_LOG")
public class ReceiptLog extends Model<ReceiptLog> {

    /**
     * 自增ID
     */
    private BigDecimal id;

    /** 操作时间 */
    private Date opTime;

    /** 对象类型 */
    private String targetType;

    /** 对象ID */
    private String targetId;

    /** 源ID */
    private String sourceId;

    /** 操作人ID */
    private String wrOperatorId;

    /** 操作人名 */
    private String operatorName;

    /** 动作 */
    private String action;

    /** 说明 */
    private String remark;

    /**  */
    private String bussinessType;

    /**  */
    private BigDecimal changeWeight;

    /**  */
    private BigDecimal regWeightBefore;

    /**  */
    private BigDecimal frozenWeightBefore;

    /**  */
    private BigDecimal freeWeightBefore;

    /**  */
    private BigDecimal regWeightAfter;

    /**  */
    private BigDecimal frozenWeightAfter;

    /**  */
    private BigDecimal freeWeightAfter;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date gmtModify;

    /** 添加人 */
    private String userCreate;

    /** 最后修改人 */
    private String userModify;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getWrOperatorId() {
        return wrOperatorId;
    }

    public void setWrOperatorId(String wrOperatorId) {
        this.wrOperatorId = wrOperatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public BigDecimal getChangeWeight() {
        return changeWeight;
    }

    public void setChangeWeight(BigDecimal changeWeight) {
        this.changeWeight = changeWeight;
    }

    public BigDecimal getRegWeightBefore() {
        return regWeightBefore;
    }

    public void setRegWeightBefore(BigDecimal regWeightBefore) {
        this.regWeightBefore = regWeightBefore;
    }

    public BigDecimal getFrozenWeightBefore() {
        return frozenWeightBefore;
    }

    public void setFrozenWeightBefore(BigDecimal frozenWeightBefore) {
        this.frozenWeightBefore = frozenWeightBefore;
    }

    public BigDecimal getFreeWeightBefore() {
        return freeWeightBefore;
    }

    public void setFreeWeightBefore(BigDecimal freeWeightBefore) {
        this.freeWeightBefore = freeWeightBefore;
    }

    public BigDecimal getRegWeightAfter() {
        return regWeightAfter;
    }

    public void setRegWeightAfter(BigDecimal regWeightAfter) {
        this.regWeightAfter = regWeightAfter;
    }

    public BigDecimal getFrozenWeightAfter() {
        return frozenWeightAfter;
    }

    public void setFrozenWeightAfter(BigDecimal frozenWeightAfter) {
        this.frozenWeightAfter = frozenWeightAfter;
    }

    public BigDecimal getFreeWeightAfter() {
        return freeWeightAfter;
    }

    public void setFreeWeightAfter(BigDecimal freeWeightAfter) {
        this.freeWeightAfter = freeWeightAfter;
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

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserModify() {
        return userModify;
    }

    public void setUserModify(String userModify) {
        this.userModify = userModify;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
