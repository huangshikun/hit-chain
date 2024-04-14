package com.hundsun.exchange.chain.ws.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czb.chain.czbBaaS.clientsdk.client.request.DcRegisterRequestV2;
import com.czb.chain.czbBaaS.clientsdk.client.response.BlockchianResponse;
import com.czb.chain.czbBaaS.clientsdk.common.FuncParams;
import com.hundsun.exchange.chain.config.CzbBaasSdkClient;
import com.hundsun.exchange.chain.enums.ChainStatusEnum;
import com.hundsun.exchange.chain.enums.ChainTypeEnum;
import com.hundsun.exchange.chain.enums.CzbBaasResultCodeEnum;
import com.hundsun.exchange.chain.enums.ReceiptOpeEnum;
import com.hundsun.exchange.chain.util.DateUtil;
import com.hundsun.exchange.chain.ws.entity.ReceiptLog;
import com.hundsun.exchange.chain.ws.entity.WsChainReceiptBatch;
import com.hundsun.exchange.chain.ws.entity.WsChainReceiptLog;
import com.hundsun.exchange.chain.ws.mapper.ReceiptLogDao;
import com.hundsun.exchange.chain.ws.mapper.WsChainReceiptBatchDao;
import com.hundsun.exchange.chain.ws.mapper.WsChainReceiptLogDao;
import com.hundsun.exchange.chain.ws.service.IWsChainReceiptBatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 功能说明：仓单上链日志Impl<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Service
@Slf4j
public class WsChainReceiptBatchServiceImpl extends ServiceImpl<WsChainReceiptBatchDao, WsChainReceiptBatch> implements IWsChainReceiptBatchService {

    @Autowired
    private WsChainReceiptLogDao wsChainReceiptLogDao;

    @Autowired
    private ReceiptLogDao receiptLogDao;

    @Autowired
    private CzbBaasSdkClient czbBaasSdkClient;

    @Value("${chain.batch.size:50}")
    private int size;

    @Override
    public void push() {
        // 查询最新的库存上链日志
        WsChainReceiptBatch wsChainReceiptBatch = baseMapper.selectLastBatch();
        if (ChainStatusEnum.FAIL.getCode().equals(wsChainReceiptBatch.getChainStatus())) {
            WsChainReceiptLog wsChainReceiptLog = wsChainReceiptLogDao.selectById(wsChainReceiptBatch.getId());

            wsChainReceiptBatch.setPushTimes(wsChainReceiptBatch.getPushTimes().intValue() + 1);
            wsChainReceiptBatch.setGmtModify(new Date());
            wsChainReceiptBatch.setPushDate(DateUtil.getDateInt(wsChainReceiptBatch.getGmtModify()));

            // 处理失败重新上链
            if (!CzbBaasResultCodeEnum.checkResult(send(wsChainReceiptLog))) {
                this.updateById(wsChainReceiptBatch);
                return;
            }

            wsChainReceiptLog.setGmtModify(wsChainReceiptBatch.getGmtModify());
            wsChainReceiptLogDao.updateById(wsChainReceiptLog);
            // 上链成功更新日志
            wsChainReceiptBatch.setChainStatus(ChainStatusEnum.SUCCESS.getCode());
            this.updateById(wsChainReceiptBatch);
        }

        List<ReceiptLog> receiptLogList = receiptLogDao.selectRegionReceiptLog(wsChainReceiptBatch.getId(), size);
        if (receiptLogList.isEmpty()) {
            return;
        }

        // 清除可能已处理日志，防止重复处理
        QueryWrapper<WsChainReceiptLog> wrapper = new QueryWrapper<WsChainReceiptLog>().gt("id", wsChainReceiptBatch.getId());
        wsChainReceiptLogDao.delete(wrapper);

        WsChainReceiptLog wsChainReceiptLog = null;
        for (ReceiptLog receiptLog : receiptLogList) {
            wsChainReceiptLog = new WsChainReceiptLog();
            wsChainReceiptLog.setId(receiptLog.getId());
            wsChainReceiptLog.setReceiptId(receiptLog.getTargetId());
            wsChainReceiptLog.setTotalWeight(receiptLog.getFreeWeightAfter());
            wsChainReceiptLog.setChangeWeight(receiptLog.getChangeWeight());
            wsChainReceiptLog.setOwnerId(receiptLog.getWrOperatorId());
            wsChainReceiptLog.setOperatorId(receiptLog.getUserModify());
            wsChainReceiptLog.setTransactionType(receiptLog.getTargetType());
            wsChainReceiptLog.setTransactionDate(receiptLog.getGmtCreate());
            wsChainReceiptLog.setParentReceiptId(receiptLog.getSourceId());
            wsChainReceiptLog.setAction(receiptLog.getAction());
            if (!CzbBaasResultCodeEnum.checkResult(send(wsChainReceiptLog))) {
                wsChainReceiptBatch = new WsChainReceiptBatch();
                wsChainReceiptBatch.setId(wsChainReceiptLog.getId());
                wsChainReceiptBatch.setChainStatus(ChainStatusEnum.FAIL.getCode());
                wsChainReceiptBatch.setGmtCreate(new Date());
                wsChainReceiptBatch.setGmtModify(wsChainReceiptBatch.getGmtCreate());
                wsChainReceiptBatch.setPushDate(DateUtil.getDateInt(wsChainReceiptBatch.getGmtCreate()));
                wsChainReceiptLogDao.insert(wsChainReceiptLog);
                this.save(wsChainReceiptBatch);
                return;
            }
            wsChainReceiptLogDao.insert(wsChainReceiptLog);
        }

        if (null != wsChainReceiptLog) {
            wsChainReceiptBatch = new WsChainReceiptBatch();
            wsChainReceiptBatch.setId(wsChainReceiptLog.getId());
            wsChainReceiptBatch.setChainStatus(ChainStatusEnum.SUCCESS.getCode());
            wsChainReceiptBatch.setGmtCreate(new Date());
            wsChainReceiptBatch.setGmtModify(wsChainReceiptBatch.getGmtCreate());
            wsChainReceiptBatch.setPushDate(DateUtil.getDateInt(wsChainReceiptBatch.getGmtCreate()));
            this.save(wsChainReceiptBatch);
        }
    }

    /**
     * 仓单上链发送
     * @param wsChainReceiptLog
     * @return
     */
    private String send(WsChainReceiptLog wsChainReceiptLog) {
        // 构造请求
        DcRegisterRequestV2 request = new DcRegisterRequestV2();
        // 传入流水号
        request.setFlowid(wsChainReceiptLog.getId().toString());
        FuncParams params = new FuncParams();
        String[] bytes32Info = new String[14];
        bytes32Info[0] = ChainTypeEnum.RECEIPT.getCode();
        bytes32Info[1] = wsChainReceiptLog.getId().toString();
        bytes32Info[2] = wsChainReceiptLog.getReceiptId();
        bytes32Info[3] = "";
        bytes32Info[4] = bytes32Info[2];
        bytes32Info[5] = null == wsChainReceiptLog.getParentReceiptId() ? "" : wsChainReceiptLog.getParentReceiptId();
        bytes32Info[6] = null == wsChainReceiptLog.getOwnerId() ? "" : wsChainReceiptLog.getOwnerId();
        bytes32Info[7] = "";
        bytes32Info[8] = null == wsChainReceiptLog.getTotalWeight() ? "" : wsChainReceiptLog.getTotalWeight().toString();
        bytes32Info[9] = "";
        bytes32Info[10] = null == wsChainReceiptLog.getOperatorId() ? "" : wsChainReceiptLog.getOperatorId();
        bytes32Info[11] = wsChainReceiptLog.getTransactionType();
        bytes32Info[12] = DateUtil.getDateTimeString(wsChainReceiptLog.getTransactionDate());
        bytes32Info[13] = bytes32Info[0] + bytes32Info[1];
        // 依次传入合约方法参数
        params.addParams(bytes32Info);
        params.addParams(null == wsChainReceiptLog.getAction() ? "" : wsChainReceiptLog.getAction());
        params.addParams(null == wsChainReceiptLog.getChangeWeight() ? "" : wsChainReceiptLog.getChangeWeight().toString());
        params.addParams("");
        request.setParams(params);
        // 传入合约方法名
        request.setMethod("registerDeal(bytes32[],bytes,bytes,bytes)");
        // 发送请求，获取响应结果
        try {
            log.info("仓单上链请求报文：[{}]", JSONObject.toJSONString(request));
            BlockchianResponse response = czbBaasSdkClient.send(request);
            wsChainReceiptLog.setRemark(JSONObject.toJSONString(response));
            log.info("仓单上链响应报文：[{}]", wsChainReceiptLog.getRemark());
            return response.getErrcode();
        } catch (Exception e) {
            log.error("仓单上链接口调用失败", e);
            return CzbBaasResultCodeEnum.EXCEPTION.getCode();
        }
    }

    /**
     * 解析质检日期变更信息
     * @param action
     * @param transactionType
     * @return
     */
    private String getAssetsInfo(String action, String transactionType) {
        if (ReceiptOpeEnum.QUALITY_DATE_CHANGE.getCode().equals(transactionType)) {
            action = action.trim();
            return action.substring(0, action.indexOf("<")) + action.substring(action.lastIndexOf(">") + 1);
        }

        return null;
    }
}
