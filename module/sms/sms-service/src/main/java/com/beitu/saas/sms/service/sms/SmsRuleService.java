package com.beitu.saas.sms.service.sms;

import com.beitu.saas.sms.ro.RuleRO;

import java.util.Date;
import java.util.List;

public interface SmsRuleService {

    /**
     * 获取规则内容
     */
    List<RuleRO> getRules(String bizCode);

    /**
     * 获取发送时间
     */
    Date getSendTime(List<RuleRO> ruleROList);

}
