package com.hundsun.exchange.chain.wh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hundsun.exchange.chain.wh.entity.WhChainInventoryBatch;

/**
 * 功能说明：库存上链批次Dao<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public interface WhChainInventoryBatchDao extends BaseMapper<WhChainInventoryBatch> {

    /**
     * 获取最新库存批次信息
     * @return
     */
    WhChainInventoryBatch selectLastBatch();

    /**
     * 获取是否有待提醒的批次信息
     * @param pushTimes 推送次数
     * @param warnTimes 提醒次数
     * @return
     */
    int selectWarnBatch(int pushTimes, int warnTimes);

    /**
     * 更新提醒信息
     * @param pushTimes 推送次数
     * @param warnTimes 提醒次数
     */
    void updateWarnTimes(int pushTimes, int warnTimes);
}
