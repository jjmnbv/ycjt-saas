package com.beitu.saas.sms.dao.impl;
import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.sms.dao.SmsMessageDao;
import com.beitu.saas.sms.entity.SmsMessage;
import com.beitu.saas.sms.model.MsgInfo;
import org.springframework.stereotype.Repository;

/**
* User: jungle
* Date: 2018-02-27
* Time: 17:09:17.449
*/

@Repository
public class SmsMessageDaoImpl extends AbstractBaseMapper<SmsMessage> implements SmsMessageDao {

    @Override
    public SmsMessage saveMessage(MsgInfo msgInfo) {
        SmsMessage entity = new SmsMessage();
        entity.setBizMsgId(msgInfo.getBizMsgId());
        entity.setThirdMsgId(msgInfo.getThirdMsgId());
        entity.setMsgTmpId(msgInfo.getMsgTmpId());
        entity.setBizCode(msgInfo.getBizCode());
        entity.setMsgType(msgInfo.getMsgType());
        entity.setServicerCode(msgInfo.getServicerCode());
        entity.setReceiver(msgInfo.getReceiver());
        entity.setContent(msgInfo.getContent());
        entity.setSendType(msgInfo.getSendType());
        entity.setAgingType(msgInfo.getAgeingType());
        entity.setStatus(msgInfo.getStatus());
        entity.setSendTime(msgInfo.getSendTime());
        entity.setExtInfo(msgInfo.getExtInfo());
        return insert(entity);
    }

}