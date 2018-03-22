package com.beitu.saas.sms.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: jungle
* Date: 2018-02-28
* Time: 15:46:53.760
* TableDesc:服务商信息表
*/
public class SmsMessage extends BaseEntity{
    /**
    *业务消息 ID
    */
    private String bizMsgId;
    /**
    *第三方消息 ID（流水号）
    */
    private String thirdMsgId;
    /**
    *消息模板id
    */
    private Integer msgTmpId;
    /**
    *业务功能码(每个app下不可再细分的功能-使用消息)
    */
    private String bizCode;
    /**
    *1、短信 2、语音 3、邮件 4、站内push
    */
    private Integer msgType;
    /**
    *服务商code
    */
    private String servicerCode;
    /**
    *收消息账号  push为userid
    */
    private String receiver;
    /**
    *消息内容
    */
    private String content;
    /**
    *发送类型：0 单发  1 群发
    */
    private Integer sendType;
    /**
    *发送时效性：0 即时  1 定时
    */
    private Integer agingType;
    /**
    *状态：0 待发送 1 已发送 2 发送失败 3 发送成功已确认
    */
    private Integer status;
    /**
    *消息发送时间
    */
    private Date sendTime;
    /**
    *备用字段 存user_code
    */
    private String extInfo;


    public  String getBizMsgId(){
        return this.bizMsgId;
    }

    public  SmsMessage setBizMsgId(String bizMsgId){
        this.bizMsgId = bizMsgId;
        return this;
    }

    public  String getThirdMsgId(){
        return this.thirdMsgId;
    }

    public  SmsMessage setThirdMsgId(String thirdMsgId){
        this.thirdMsgId = thirdMsgId;
        return this;
    }

    public  Integer getMsgTmpId(){
        return this.msgTmpId;
    }

    public  SmsMessage setMsgTmpId(Integer msgTmpId){
        this.msgTmpId = msgTmpId;
        return this;
    }

    public  String getBizCode(){
        return this.bizCode;
    }

    public  SmsMessage setBizCode(String bizCode){
        this.bizCode = bizCode;
        return this;
    }

    public  Integer getMsgType(){
        return this.msgType;
    }

    public  SmsMessage setMsgType(Integer msgType){
        this.msgType = msgType;
        return this;
    }

    public  String getServicerCode(){
        return this.servicerCode;
    }

    public  SmsMessage setServicerCode(String servicerCode){
        this.servicerCode = servicerCode;
        return this;
    }

    public  String getReceiver(){
        return this.receiver;
    }

    public  SmsMessage setReceiver(String receiver){
        this.receiver = receiver;
        return this;
    }

    public  String getContent(){
        return this.content;
    }

    public  SmsMessage setContent(String content){
        this.content = content;
        return this;
    }

    public  Integer getSendType(){
        return this.sendType;
    }

    public  SmsMessage setSendType(Integer sendType){
        this.sendType = sendType;
        return this;
    }

    public  Integer getAgingType(){
        return this.agingType;
    }

    public  SmsMessage setAgingType(Integer agingType){
        this.agingType = agingType;
        return this;
    }

    public  Integer getStatus(){
        return this.status;
    }

    public  SmsMessage setStatus(Integer status){
        this.status = status;
        return this;
    }

    public  Date getSendTime(){
        return this.sendTime;
    }

    public  SmsMessage setSendTime(Date sendTime){
        this.sendTime = sendTime;
        return this;
    }

    public  String getExtInfo(){
        return this.extInfo;
    }

    public  SmsMessage setExtInfo(String extInfo){
        this.extInfo = extInfo;
        return this;
    }
}
