package com.hundsun.exchange.chain.wh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hundsun.exchange.chain.wh.entity.WhChainInventoryBatch;

/**
 * 功能说明：库存上链日志Service<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public interface IWhChainInventoryBatchService extends IService<WhChainInventoryBatch> {

    /**
     * 库存上链推送
     */
    void push();
}
