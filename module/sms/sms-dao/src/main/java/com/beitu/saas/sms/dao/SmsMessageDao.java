package com.beitu.saas.sms.dao;

import com.beitu.saas.sms.entity.SmsMessage;
import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.sms.model.MsgInfo;

/**
 * User: jungle
 * Date: 2018-02-27
 * Time: 17:09:17.448
 */

public interface SmsMessageDao extends BaseMapper<SmsMessage> {

    /**
     * 保存消息
     *
     * @param msgInfo
     * @return
     */
    SmsMessage saveMessage(MsgInfo msgInfo);

}