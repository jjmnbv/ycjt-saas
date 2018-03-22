package com.beitu.saas.sms.service.impl;

import com.beitu.saas.sms.ro.VoiceMsgRequestRO;
import com.beitu.saas.sms.service.sms.SmsRuleService;
import com.beitu.saas.sms.ro.RuleRO;
import com.beitu.saas.sms.client.plugin.SendVoiceVerifyPluginUtils;
import com.beitu.saas.sms.ro.BatchSmsSendRquestRO;
import com.beitu.saas.sms.client.SmsMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.sms.ro.SingleSmsSendRequestRO;
import com.beitu.saas.sms.service.sms.SmsMsgDataService;
import com.beitu.saas.sms.ro.Result;
import com.beitu.saas.sms.ro.Results;
import com.beitu.saas.sms.ro.StateCode;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 短信消息服务
 */
@Service
public class SmsMsgServiceImpl implements SmsMsgService {

    private static Log LOGGER = LogFactory.getLog(SmsMsgService.class);

    @Autowired
    private SmsMsgDataService smsMsgDataService;

    @Autowired
    private SmsRuleService smsRuleService;

    /**
     * 发送单条短信
     * @param requestRO
     * @return
     */
    @Override
    public Result<Boolean> send(SingleSmsSendRequestRO requestRO) {
        if(null == requestRO){
            LOGGER.info("params can not be null");
            return Results.newFailedResult(false, StateCode.PARAMETER_LACK);
        }
        if(null == requestRO.getPhone()){
            LOGGER.info("phone is required");
            return Results.newFailedResult(false,StateCode.ILLEGAL_PARAMS,"phone must not be null");
        }
        if (requestRO.getAppId() == 8 && null == requestRO.getBizCode()){
            requestRO.setBizCode("JKZJ_VOICE_0000");
        }
        if(null == requestRO.getBizCode()){
            LOGGER.info("bizCode is required");
            return Results.newFailedResult(false,StateCode.ILLEGAL_PARAMS,"bizCode must not be null");
        }

        // 设置短信发送时间
        List<RuleRO> rules = smsRuleService.getRules(requestRO.getBizCode());
        if (!CollectionUtils.isEmpty(rules)) {
            requestRO.setSendTime(smsRuleService.getSendTime(rules));
        }

        StateCode stateCode = smsMsgDataService.singleSend(requestRO);
        if(stateCode.isSuccess()){
            return Results.newSuccessResult(true);
        }else {
            return Results.newFailedResult(false,stateCode);
        }

    }



    @Override
    public Result<Boolean> batchSend(BatchSmsSendRquestRO requestRO) {
        if(null == requestRO){
            LOGGER.info("params can not be null");
            return Results.newFailedResult(false,StateCode.PARAMETER_LACK);
        }
        if(null == requestRO.getContents()){
            LOGGER.info("phone is required");
            return Results.newFailedResult(false,StateCode.ILLEGAL_PARAMS,"phone、content must not be null");
        }
        if(null == requestRO.getBizCode()){
            LOGGER.info("bizCode is required");
            return Results.newFailedResult(false,StateCode.ILLEGAL_PARAMS,"bizCode must not be null");
        }
        StateCode stateCode = smsMsgDataService.batchSend(requestRO);
        if(stateCode.isSuccess()){
            return Results.newSuccessResult(true);
        }else {
            return Results.newFailedResult(false,stateCode);
        }
    }

    @Override
    public Result<Boolean> sendVoiceMsg(VoiceMsgRequestRO requestRO) {
        if(null == requestRO){
            LOGGER.info("params can not be null");
            return Results.newFailedResult(false, StateCode.PARAMETER_LACK);
        }
        if(null == requestRO.getPhone()){
            LOGGER.info("phone is required");
            return Results.newFailedResult(false,StateCode.ILLEGAL_PARAMS,"phone must not be null");
        }
        if(null == requestRO.getBizCode()){
            LOGGER.info("bizCode is required");
            return Results.newFailedResult(false,StateCode.ILLEGAL_PARAMS,"bizCode must not be null");
        }
        if (null == requestRO.getVerifyCode()) {
            LOGGER.info("verifycode is required");
            return Results.newFailedResult(false, StateCode.ILLEGAL_PARAMS, "verifycode must not be null");
        }
        StateCode stateCode = SendVoiceVerifyPluginUtils.send(requestRO.getPhone(), requestRO.getVerifyCode());
        if(stateCode.isSuccess()){
            return Results.newSuccessResult(true);
        }else {
            return Results.newFailedResult(false,stateCode);
        }

    }

}
