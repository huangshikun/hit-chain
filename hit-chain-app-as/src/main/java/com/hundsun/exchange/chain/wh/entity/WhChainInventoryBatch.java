package com.hundsun.exchange.chain.wh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能说明：库存上链批次表<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@TableName("WH_CHAIN_INVENTORY_BATCH")
public class WhChainInventoryBatch extends Model<WhChainInventoryBatch> {

    /**
     * 自增ID
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /** 上链状态 */
    private String chainStatus;

    /** 推送日期 */
    private Integer pushDate;

    /** 推送次数 */
    private Integer pushTimes;

    /** 短信次数 */
    private Integer warnTimes;

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

    public String getChainStatus() {
        return chainStatus;
    }

    public void setChainStatus(String chainStatus) {
        this.chainStatus = chainStatus;
    }

    public Integer getPushDate() {
        return pushDate;
    }

    public void setPushDate(Integer pushDate) {
        this.pushDate = pushDate;
    }

    public Integer getPushTimes() {
        return pushTimes;
    }

    public void setPushTimes(Integer pushTimes) {
        this.pushTimes = pushTimes;
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

    public Integer getWarnTimes() {
        return warnTimes;
    }

    public void setWarnTimes(Integer warnTimes) {
        this.warnTimes = warnTimes;
    }
}
