package com.hundsun.exchange.chain.wh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hundsun.exchange.chain.wh.entity.WhInventoryChange;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能说明：库存变更Dao<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public interface WhInventoryChangeDao extends BaseMapper<WhInventoryChange> {

    /**
     * 采集库存变更日志
     * @param id 起始ID
     * @param size 批次数量
     * @return
     */
    List<WhInventoryChange> selectRegionInventoryChange(BigDecimal id, int size);
}
