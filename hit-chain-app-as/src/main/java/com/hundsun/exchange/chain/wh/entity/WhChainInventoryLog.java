package com.hundsun.exchange.chain.wh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能说明：库存上链日志表<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@TableName("WH_CHAIN_INVENTORY_LOG")
public class WhChainInventoryLog extends Model<WhChainInventoryLog> {

    /**
     * 自增ID
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /** 库存ID */
    private BigDecimal inventoryId;

    /** 总量 */
    private BigDecimal totalWeight;

    /** 变更量 */
    private BigDecimal changeWeight;

    /** 持有人ID */
    private String ownerId;

    /** 操作人ID */
    private String operatorId;

    /** 交易类型 */
    private String transactionType;

    /** 交易日期 */
    private Date transactionDate;

    /** 父级库存ID */
    private BigDecimal parentInventoryId;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date gmtModify;

    /** 备注 */
    private String remark;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(BigDecimal inventoryId) {
        this.inventoryId = inventoryId;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getChangeWeight() {
        return changeWeight;
    }

    public void setChangeWeight(BigDecimal changeWeight) {
        this.changeWeight = changeWeight;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getParentInventoryId() {
        return parentInventoryId;
    }

    public void setParentInventoryId(BigDecimal parentInventoryId) {
        this.parentInventoryId = parentInventoryId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
