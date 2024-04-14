package com.hundsun.exchange.chain.controller;


import com.alibaba.fastjson.JSON;
import com.hundsun.exchange.chain.uc.service.IMsgMessageInfoService;
import com.hundsun.exchange.chain.wh.service.IWhChainInventoryBatchService;
import com.hundsun.exchange.chain.ws.service.IWsChainReceiptBatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明：上链接口<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 *
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Slf4j
@RestController
@RequestMapping("/chain")
public class ChainController {

    @Autowired
    private IWsChainReceiptBatchService wsChainReceiptBatchService;

    @Autowired
    private IWhChainInventoryBatchService whChainInventoryBatchService;

    @Autowired
    private IMsgMessageInfoService msgMessageInfoService;

    @RequestMapping("/push")
    public String push() {
        try {
            whChainInventoryBatchService.push();
        } catch (Exception e) {
            log.error("库存上链失败", e);
        }
        try {
            wsChainReceiptBatchService.push();
        } catch (Exception e) {
            log.error("仓单上链失败", e);
        }

        return "finish";
    }

    @RequestMapping("/smsWarn")
    public String smsWarn() {
        msgMessageInfoService.smsWarn();
        return "finish";
    }

    @RequestMapping("/test")
    public String test() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", 2);
        map.put("message", "{\"code\":\"1\",\"message\":\"\"}");
        return JSON.toJSONString(map);
    }
}
