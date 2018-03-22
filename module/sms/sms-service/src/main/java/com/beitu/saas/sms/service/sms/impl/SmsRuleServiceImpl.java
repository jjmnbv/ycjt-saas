package com.beitu.saas.sms.service.sms.impl;


import com.alibaba.fastjson.JSON;
import com.beitu.saas.common.utils.DateUtil;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.sms.model.BusinessRefInfo;
import com.beitu.saas.sms.ro.RuleRO;
import com.beitu.saas.sms.service.sms.SmsRuleService;
import com.beitu.saas.sms.util.CacheKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class SmsRuleServiceImpl implements SmsRuleService {

    private static Log LOGGER = LogFactory.getLog(SmsRuleService.class);

    @Autowired
    private RedisClient redisClient;

    /**
     * 获取短信服务配置信息
     */
    private BusinessRefInfo getBusinessRefInfo(String bizCode) {
        String value = redisClient.get(CacheKey.getKeyOfMessageConfig(bizCode));
        List<BusinessRefInfo> configList = JSON.parseArray(value, BusinessRefInfo.class);
        if (CollectionUtils.isEmpty(configList)) {
            LOGGER.info("配置信息错误, biz_code = ：{}", bizCode);
            return new BusinessRefInfo();
        }
        configList.sort(Comparator.comparingInt(BusinessRefInfo::getRank));
        return configList.get(0);
    }

    /**
     * 获取规则配置信息
     */
    @Override
    public List<RuleRO> getRules(String bizCode) {
        BusinessRefInfo businessRefInfo = getBusinessRefInfo(bizCode);
        String ruleContent = businessRefInfo.getRuleContent();
        if (StringUtils.isBlank(ruleContent)) {
            return null;
        }
        return JSON.parseArray(ruleContent, RuleRO.class);
    }

    /**
     * 获取发送时间
     */
    @Override
    public Date getSendTime(List<RuleRO> ruleROList) {
        Date nowTime = new Date();
        if (CollectionUtils.isEmpty(ruleROList)) {
            return null;
        }
        for (RuleRO rule : ruleROList) {
            if ("sendTime".equals(rule.getFact())) {
                Date sendStartDate = DateUtil.getStrDate(DateUtil.convertDateToString(nowTime) + " " + rule.getStartSendTime(), DateUtil.getDateTimePattern());
                if (DateUtil.diffDateToMinute(nowTime, sendStartDate) > 0) {
                    return nowTime;
                } else {
                    return sendStartDate;
                }
            }
        }
        return null;
    }
}
