package com.beitu.saas.sms.client;

import com.beitu.saas.sms.ro.VoiceMsgRequestRO;
import com.beitu.saas.sms.ro.BatchSmsSendRquestRO;
import com.beitu.saas.sms.ro.SingleSmsSendRequestRO;
import com.beitu.saas.sms.ro.Result;

public interface SmsMsgService {

    /**
     * 发送单条短信服务  短信验证码 replaceParam.put("VERIFY_CODE","1234")
     */
    Result<Boolean> send(SingleSmsSendRequestRO requestRO);

    /**
     * 批量发送短信（同模板，可有替换参数）
     */
    Result<Boolean> batchSend(BatchSmsSendRquestRO requestRO);

    /**
     * 语音验证码
     */
    Result<Boolean> sendVoiceMsg(VoiceMsgRequestRO requestRO);
}
