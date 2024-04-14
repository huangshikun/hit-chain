package com.hundsun.exchange;

import com.hundsun.exchange.chain.Application;
import com.hundsun.exchange.chain.uc.entity.MsgMessageInfo;
import com.hundsun.exchange.chain.uc.mapper.MsgMessageInfoDao;
import com.hundsun.exchange.chain.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 功能说明：测试类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    private Logger logger = LogManager.getLogger(ApplicationTest.class);

    @Autowired
    private MsgMessageInfoDao msgMessageInfoDao;

    @Test
    public void mingle() {
        MsgMessageInfo msgMessageInfo = new MsgMessageInfo();
        msgMessageInfo.setSubject("1");
        msgMessageInfo.setTemplate("1");
        msgMessageInfo.setDestinate("186");
        msgMessageInfo.setOperateId("-1");
        msgMessageInfo.setOperateName("交易平台");
        msgMessageInfo.setSendTime(DateUtil.getDateBigDecimal(new Date()));
        msgMessageInfo.setGmtCreate(msgMessageInfo.getSendTime());
        msgMessageInfo.setGmtModify(msgMessageInfo.getSendTime());
        msgMessageInfo.setIsSend("N");
        msgMessageInfo.setType("sms");
        msgMessageInfo.setContent("1");
        msgMessageInfoDao.insert(msgMessageInfo);
    }

    public static void main(String[] a) {

        String b = "\n" +
                "仓单<span name=\"spn_receipt\" bean_no=\"RY93WYccg121081600041\">RY93WYccg121081600041</span>因重量变更由原重量45.000吨变更为54吨";
        b = b.trim();
        System.out.println(b.substring(0, b.indexOf("<")) + b.substring(b.lastIndexOf(">") + 1));
    }
}
