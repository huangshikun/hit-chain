package com.hundsun.exchange.chain.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理类
 * @author huangsk20406
 * @date 2020-10-27
 */
@Slf4j
public class DateUtil {

    public static String getDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static int getDateInt(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(date));
    }

    public static BigDecimal getDateBigDecimal(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return new BigDecimal(sdf.format(date));
    }
}
