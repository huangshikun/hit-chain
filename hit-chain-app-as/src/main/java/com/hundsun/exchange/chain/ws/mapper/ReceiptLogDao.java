package com.hundsun.exchange.chain.ws.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hundsun.exchange.chain.ws.entity.ReceiptLog;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能说明：仓单日志dao<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public interface ReceiptLogDao extends BaseMapper<ReceiptLog> {

    /**
     * 采集仓单变更日志
     * @param id 起始ID
     * @param size 批次数量
     * @return
     */
    List<ReceiptLog> selectRegionReceiptLog(BigDecimal id, int size);
}
