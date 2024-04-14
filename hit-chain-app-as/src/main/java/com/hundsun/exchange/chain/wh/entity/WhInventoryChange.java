package com.hundsun.exchange.chain.wh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能说明：库存变更表<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@TableName("WH_INVENTORY_CHANGE")
public class WhInventoryChange extends Model<WhInventoryChange> {

    /** 主键ID */
    private BigDecimal id;

    /** 库存ID */
    private BigDecimal inventoryId;

    /** 源ID */
    private BigDecimal sourceId;

    /** 品种编码 */
    private String varietyCode;

    /** 品种名称 */
    private String varietyName;

    /** 变更重量 */
    private BigDecimal changeWeight;

    /** 变更类型 */
    private String changeType;

    /** 库存总重量 */
    private BigDecimal totalWeight;

    /** 变化类型： 0是总量变化 1是明细变化 */
    private Integer model;

    /** 变更前库存重量 */
    private BigDecimal beforeChangeWeight;

    /** 说明 */
    private String explanation;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date gmtModify;

    /** 添加人 */
    private String userCreate;

    /** 最后修改人 */
    private String userModify;

    /** 品种标准类型  1.在库标准仓单 2.在库非标准仓单 3.外库标准仓单 4.外库非标准仓单*/
    private String varietyReceiptType;

    /** 品种记账类型 1、记账 2、定额 */
    private String varietyAccountType;

    /** 品种类型 1、通用型 2、半通用型 3、非通用型*/
    private String varietyType;

    /** 存货方ID */
    private String owerCompanyId;

    /** 存货方名称 */
    private String owerCompanyName;

    /** 交收机构ID */
    private BigDecimal warehouseId;

    /** 交收机构名称 */
    private String warehouseName;

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

    public String getVarietyCode() {
        return varietyCode;
    }

    public void setVarietyCode(String varietyCode) {
        this.varietyCode = varietyCode;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public BigDecimal getChangeWeight() {
        return changeWeight;
    }

    public void setChangeWeight(BigDecimal changeWeight) {
        this.changeWeight = changeWeight;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public BigDecimal getBeforeChangeWeight() {
        return beforeChangeWeight;
    }

    public void setBeforeChangeWeight(BigDecimal beforeChangeWeight) {
        this.beforeChangeWeight = beforeChangeWeight;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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

    public String getVarietyReceiptType() {
        return varietyReceiptType;
    }

    public void setVarietyReceiptType(String varietyReceiptType) {
        this.varietyReceiptType = varietyReceiptType;
    }

    public String getVarietyAccountType() {
        return varietyAccountType;
    }

    public void setVarietyAccountType(String varietyAccountType) {
        this.varietyAccountType = varietyAccountType;
    }

    public String getVarietyType() {
        return varietyType;
    }

    public void setVarietyType(String varietyType) {
        this.varietyType = varietyType;
    }

    public String getOwerCompanyId() {
        return owerCompanyId;
    }

    public void setOwerCompanyId(String owerCompanyId) {
        this.owerCompanyId = owerCompanyId;
    }

    public String getOwerCompanyName() {
        return owerCompanyName;
    }

    public void setOwerCompanyName(String owerCompanyName) {
        this.owerCompanyName = owerCompanyName;
    }

    public BigDecimal getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(BigDecimal warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public BigDecimal getSourceId() {
        return sourceId;
    }

    public void setSourceId(BigDecimal sourceId) {
        this.sourceId = sourceId;
    }
}
