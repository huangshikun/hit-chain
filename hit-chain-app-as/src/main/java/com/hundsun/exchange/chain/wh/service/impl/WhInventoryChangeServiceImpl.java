package com.hundsun.exchange.chain.wh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.exchange.chain.wh.entity.WhInventoryChange;
import com.hundsun.exchange.chain.wh.mapper.WhInventoryChangeDao;
import com.hundsun.exchange.chain.wh.service.IWhInventoryChangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能说明：库存变更Impl<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Service
@Slf4j
public class WhInventoryChangeServiceImpl extends ServiceImpl<WhInventoryChangeDao, WhInventoryChange> implements IWhInventoryChangeService {
}
