package com.hundsun.exchange.chain.wh.service.impl;

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
import com.hundsun.exchange.chain.util.DateUtil;
import com.hundsun.exchange.chain.wh.entity.WhChainInventoryBatch;
import com.hundsun.exchange.chain.wh.entity.WhChainInventoryLog;
import com.hundsun.exchange.chain.wh.entity.WhInventoryChange;
import com.hundsun.exchange.chain.wh.mapper.WhChainInventoryBatchDao;
import com.hundsun.exchange.chain.wh.mapper.WhChainInventoryLogDao;
import com.hundsun.exchange.chain.wh.mapper.WhInventoryChangeDao;
import com.hundsun.exchange.chain.wh.service.IWhChainInventoryBatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 功能说明：库存上链日志Impl<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Service
@Slf4j
public class WhChainInventoryBatchServiceImpl extends ServiceImpl<WhChainInventoryBatchDao, WhChainInventoryBatch> implements IWhChainInventoryBatchService {

    @Autowired
    private WhChainInventoryLogDao whChainInventoryLogDao;

    @Autowired
    private WhInventoryChangeDao whInventoryChangeDao;

    @Autowired
    private CzbBaasSdkClient czbBaasSdkClient;

    @Value("${chain.batch.size:50}")
    private int size;

    @Override
    public void push() {
        // 查询最新的库存上链日志
        WhChainInventoryBatch whChainInventoryBatch = this.baseMapper.selectLastBatch();
        if (ChainStatusEnum.FAIL.getCode().equals(whChainInventoryBatch.getChainStatus())) {
            WhChainInventoryLog whChainInventoryLog = whChainInventoryLogDao.selectById(whChainInventoryBatch.getId());

            whChainInventoryBatch.setPushTimes(whChainInventoryBatch.getPushTimes().intValue() + 1);
            whChainInventoryBatch.setGmtModify(new Date());
            whChainInventoryBatch.setPushDate(DateUtil.getDateInt(whChainInventoryBatch.getGmtModify()));

            // 处理失败重新上链
            if (!CzbBaasResultCodeEnum.checkResult(send(whChainInventoryLog))) {
                this.updateById(whChainInventoryBatch);
                return;
            }

            whChainInventoryLog.setGmtModify(whChainInventoryBatch.getGmtModify());
            whChainInventoryLogDao.updateById(whChainInventoryLog);
            // 上链成功更新日志
            whChainInventoryBatch.setChainStatus(ChainStatusEnum.SUCCESS.getCode());
            this.updateById(whChainInventoryBatch);
        }

        List<WhInventoryChange> whInventoryChangeList = whInventoryChangeDao.selectRegionInventoryChange(whChainInventoryBatch.getId(), size);
        if (whInventoryChangeList.isEmpty()) {
            return;
        }

        // 清除可能已处理日志，防止重复处理
        QueryWrapper<WhChainInventoryLog> wrapper = new QueryWrapper<WhChainInventoryLog>().gt("id", whChainInventoryBatch.getId());
        whChainInventoryLogDao.delete(wrapper);

        WhChainInventoryLog whChainInventoryLog = null;
        for (WhInventoryChange whInventoryChange : whInventoryChangeList) {
            whChainInventoryLog = new WhChainInventoryLog();
            whChainInventoryLog.setId(whInventoryChange.getId());
            whChainInventoryLog.setInventoryId(whInventoryChange.getInventoryId());
            whChainInventoryLog.setTotalWeight(whInventoryChange.getTotalWeight());
            whChainInventoryLog.setChangeWeight(whInventoryChange.getChangeWeight());
            whChainInventoryLog.setOwnerId(whInventoryChange.getOwerCompanyId());
            whChainInventoryLog.setOperatorId(whInventoryChange.getUserModify());
            whChainInventoryLog.setTransactionType(whInventoryChange.getChangeType());
            whChainInventoryLog.setTransactionDate(whInventoryChange.getGmtCreate());
            whChainInventoryLog.setParentInventoryId(whInventoryChange.getSourceId());
            if (!CzbBaasResultCodeEnum.checkResult(send(whChainInventoryLog))) {
                whChainInventoryBatch = new WhChainInventoryBatch();
                whChainInventoryBatch.setId(whChainInventoryLog.getId());
                whChainInventoryBatch.setChainStatus(ChainStatusEnum.FAIL.getCode());
                whChainInventoryBatch.setGmtCreate(new Date());
                whChainInventoryBatch.setGmtModify(whChainInventoryBatch.getGmtCreate());
                whChainInventoryBatch.setPushDate(DateUtil.getDateInt(whChainInventoryBatch.getGmtCreate()));
                whChainInventoryLogDao.insert(whChainInventoryLog);
                this.save(whChainInventoryBatch);
                return;
            }
            whChainInventoryLogDao.insert(whChainInventoryLog);
        }

        if (null != whChainInventoryLog) {
            whChainInventoryBatch = new WhChainInventoryBatch();
            whChainInventoryBatch.setId(whChainInventoryLog.getId());
            whChainInventoryBatch.setChainStatus(ChainStatusEnum.SUCCESS.getCode());
            whChainInventoryBatch.setGmtCreate(new Date());
            whChainInventoryBatch.setGmtModify(whChainInventoryBatch.getGmtCreate());
            whChainInventoryBatch.setPushDate(DateUtil.getDateInt(whChainInventoryBatch.getGmtCreate()));
            this.save(whChainInventoryBatch);
        }
    }

    /**
     * 库存上链发送
     * @param whChainInventoryLog
     * @return
     */
    private String send(WhChainInventoryLog whChainInventoryLog) {
        // 构造请求
        DcRegisterRequestV2 request = new DcRegisterRequestV2();
        // 传入流水号
        request.setFlowid(whChainInventoryLog.getId().toString());
        FuncParams params = new FuncParams();
        String[] bytes32Info = new String[14];
        bytes32Info[0] = ChainTypeEnum.INVENTORY.getCode();
        bytes32Info[1] = whChainInventoryLog.getId().toString();
        bytes32Info[2] = whChainInventoryLog.getInventoryId().toString();
        bytes32Info[3] = "";
        bytes32Info[4] = bytes32Info[2];
        bytes32Info[5] = null == whChainInventoryLog.getParentInventoryId() ? "" : whChainInventoryLog.getParentInventoryId().toString();
        bytes32Info[6] = null == whChainInventoryLog.getOwnerId() ? "" : whChainInventoryLog.getOwnerId();
        bytes32Info[7] = "";
        bytes32Info[8] = null == whChainInventoryLog.getTotalWeight() ? "" : whChainInventoryLog.getTotalWeight().toString();
        bytes32Info[9] = "";
        bytes32Info[10] = null == whChainInventoryLog.getOperatorId() ? "" : whChainInventoryLog.getOperatorId();
        bytes32Info[11] = whChainInventoryLog.getTransactionType();
        bytes32Info[12] = DateUtil.getDateTimeString(whChainInventoryLog.getTransactionDate());
        bytes32Info[13] = bytes32Info[0] + bytes32Info[1];
        // 依次传入合约方法参数
        params.addParams(bytes32Info);
        params.addParams("");
        params.addParams(null == whChainInventoryLog.getChangeWeight() ? "" : whChainInventoryLog.getChangeWeight().toString());
        params.addParams("");
        request.setParams(params);
        // 传入合约方法名
        request.setMethod("registerDeal(bytes32[],bytes,bytes,bytes)");
        // 发送请求，获取响应结果
        try {
            log.info("库存上链请求报文：[{}]", JSONObject.toJSONString(request));
            BlockchianResponse response = czbBaasSdkClient.send(request);
            whChainInventoryLog.setRemark(JSONObject.toJSONString(response));
            log.info("库存上链响应报文：[{}]", whChainInventoryLog.getRemark());
            return response.getErrcode();
        } catch (Exception e) {
            log.error("库存上链接口调用失败", e);
            return CzbBaasResultCodeEnum.EXCEPTION.getCode();
        }
    }
}
