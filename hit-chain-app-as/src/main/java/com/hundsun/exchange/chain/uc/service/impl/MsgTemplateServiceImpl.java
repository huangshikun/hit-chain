package com.hundsun.exchange.chain.uc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.exchange.chain.uc.entity.MsgTemplate;
import com.hundsun.exchange.chain.uc.mapper.MsgTemplateDao;
import com.hundsun.exchange.chain.uc.service.IMsgTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能说明：信息模板Impl<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@Service
@Slf4j
public class MsgTemplateServiceImpl extends ServiceImpl<MsgTemplateDao, MsgTemplate> implements IMsgTemplateService {

    private final static String CODE = "chainWarnM";

    @Override
    public MsgTemplate getByCode() {
        QueryWrapper<MsgTemplate> queryWrapper = new QueryWrapper<MsgTemplate>().eq("code", CODE);
        return this.getOne(queryWrapper);
    }
}
