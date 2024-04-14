package com.hundsun.exchange.chain.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czb.chain.czbBaaS.clientsdk.account.Account;
import com.czb.chain.czbBaaS.clientsdk.account.Algo;
import com.czb.chain.czbBaaS.clientsdk.client.config.BlockchainConfig;
import com.czb.chain.czbBaaS.clientsdk.client.request.BlockchainRequest;
import com.czb.chain.czbBaaS.clientsdk.client.request.DcRegisterRequest;
import com.czb.chain.czbBaaS.clientsdk.client.request.DcRegisterRequestV2;
import com.czb.chain.czbBaaS.clientsdk.client.response.BlockchianResponse;
import com.czb.chain.czbBaaS.clientsdk.client.util.HttpUtil;
import com.czb.chain.czbBaaS.clientsdk.common.FuncParams;
import com.czb.chain.czbBaaS.clientsdk.common.utils.readconfig.ClientYamlConfigConstants;
import com.czb.chain.czbBaaS.clientsdk.common.utils.readconfig.YamlConfigParser;
import com.czb.chain.czbBaaS.clientsdk.transaction.utils.TransactionUtil;
import okhttp3.Response;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能说明：重新改造浙商SDK发送类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 *
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
public class CzbBaasSdkClient {

    private static final Logger log = LoggerFactory.getLogger(CzbBaasSdkClient.class);
    private BlockchainConfig config;

    public CzbBaasSdkClient(Environment env) {
        try {
            this.config = new BlockchainConfig();
            this.config.setPubkey(env.getProperty("blockchain.pubkey"));
            this.config.setPrikey(env.getProperty("blockchain.prikey"));
            this.config.setAddress(env.getProperty("blockchain.address"));
            this.config.setContractAddress(env.getProperty("blockchain.contract.address"));
            this.config.setMethodName(env.getProperty("blockchain.method.name"));
            this.config.setPwd(env.getProperty("blockchain.pwd"));
            this.config.setUrl(env.getProperty("blockchain.url"));
            this.config.setVersion(env.getProperty("blockchain.version"));
            this.config.setOpenid(env.getProperty("blockchain.openid"));
            this.config.setChannel(env.getProperty("blockchain.channel"));
        } catch (Exception var5) {
            log.error("Exception: ", var5);
        }
    }

    public BlockchianResponse send(DcRegisterRequest request) {
        String txJson = this.genTxJson(request.getFlowid(), request.getData());
        BlockchainRequest blockchainRequest = BlockchainRequest.builder().channel(this.config.getChannel()).flowid(request.getFlowid()).openid(this.config.getOpenid()).version(this.config.getVersion()).address(this.config.getAddress()).txJsonStr(txJson).sendtime(DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss")).build();
        String path = request.isAsync() ? "/dc/asyncRegister" : "/dc/syncRegister";
        BlockchianResponse response = this.execute(this.config.getUrl() + path, JSON.toJSONString(blockchainRequest));
        return response;
    }

    public BlockchianResponse send(DcRegisterRequestV2 request) {
        String txJson = this.genTxJson(request.getParams(), request.getMethod());
        BlockchainRequest blockchainRequest = BlockchainRequest.builder().channel(this.config.getChannel()).flowid(request.getFlowid()).openid(this.config.getOpenid()).version(this.config.getVersion()).address(this.config.getAddress()).txJsonStr(txJson).sendtime(DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss")).method(request.getMethod()).build();
        String path = "/dc/syncInvoke";
        BlockchianResponse response = this.execute(this.config.getUrl() + path, JSON.toJSONString(blockchainRequest));
        return response;
    }

    private BlockchianResponse execute(String url, String content) {
        BlockchianResponse blockchianResponse = new BlockchianResponse();

        try {
            Response response = HttpUtil.post(url, content);
            blockchianResponse = (BlockchianResponse) JSON.parseObject(response.body().string(), BlockchianResponse.class);
            blockchianResponse.setSuccess(true);
        } catch (IOException var5) {
            log.error("IOException: ", var5);
            blockchianResponse.setSuccess(false);
        } catch (Exception var6) {
            log.error("Exception: ", var6);
            blockchianResponse.setSuccess(false);
        }

        return blockchianResponse;
    }

    private String genTxJson(String flowid, String data) {
        Account account = Account.fromAccountJson(this.getAccountJson(), this.config.getPwd());
        FuncParams params = new FuncParams();
        String dcid = TransactionUtil.genTxFlowId(this.config.getOpenid(), flowid);
        params.addParams(flowid);
        params.addParams((new Date()).toString());
        params.addParams(dcid);
        params.addParams(data);
        return TransactionUtil.createTransactionJson(account, this.config.getContractAddress(), this.config.getMethodName(), params);
    }

    private String genTxJson(FuncParams funcParams) {
        Account account = Account.fromAccountJson(this.getAccountJson(), this.config.getPwd());
        return TransactionUtil.createTransactionJson(account, this.config.getContractAddress(), this.config.getMethodName(), funcParams);
    }

    private String genTxJson(FuncParams funcParams, String method) {
        Account account = Account.fromAccountJson(this.getAccountJson(), this.config.getPwd());
        return TransactionUtil.createTransactionJson(account, this.config.getContractAddress(), method, funcParams);
    }

    private String getAccountJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("publicKey", this.config.getPubkey());
        jsonObject.put("privateKey", this.config.getPrikey());
        jsonObject.put("address", this.config.getAddress());
        jsonObject.put("privateKeyEncrypted", true);
        jsonObject.put("version", "4.0");
        YamlConfigParser parser = YamlConfigParser.getInstance();
        Algo algo = Algo.getAlog((String) parser.getModuleConfig().get(ClientYamlConfigConstants.ENCRYPT_ALOG.getValue()));
        jsonObject.put("algo", algo.getAlgo());
        return jsonObject.toJSONString();
    }

    public String getOpenId() {
        return this.config.getOpenid();
    }
}
