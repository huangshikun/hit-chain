package com.hundsun.exchange.chain.ws.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.exchange.chain.ws.entity.ReceiptLog;
import com.hundsun.exchange.chain.ws.mapper.ReceiptLogDao;
import com.hundsun.exchange.chain.ws.service.IReceiptLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能说明：仓单日志Impl<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Service
@Slf4j
public class ReceiptLogServiceImpl extends ServiceImpl<ReceiptLogDao, ReceiptLog> implements IReceiptLogService {
}
