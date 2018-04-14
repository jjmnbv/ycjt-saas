package com.beitu.saas.sms.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-02-28
* Time: 15:46:53.770
* TableDesc:消息模板表
*/
public class SmsMessageTemplate extends BaseEntity{
    /**
    *app id
    */
    private Long appId;
    /**
    *业务功能码
    */
    private String bizCode;
    /**
    *
    */
    private Integer isCommon;
    /**
    *消息类型 1、短信 2、语音 3、邮件 4、站内push
    */
    private Integer msgType;
    /**
    *模板来源：1-本地模板；2-第三方模板
    */
    private Integer templateType;
    /**
    *消息模板内容 替换内容格式 ： ##替换内容##
    */
    private String content;
    /**
    *三方消息模板id（已弃用）
    */
    private String thirdTmpId;
    /**
    *服务商码 拼音大写
    */
    private String servicerCode;
    /**
    *替换key集合,逗号隔开
    */
    private String replaceList;
    /**
    *描述
    */
    private String descInfo;
    /**
    *短信分类：1-验证码短信，2-业务短信， 3-通知短信， 4-营销短信
    */
    private String type;
    /**
    *签名
    */
    private String sign;


    public  Long getAppId(){
        return this.appId;
    }

    public  SmsMessageTemplate setAppId(Long appId){
        this.appId = appId;
        return this;
    }

    public  String getBizCode(){
        return this.bizCode;
    }

    public  SmsMessageTemplate setBizCode(String bizCode){
        this.bizCode = bizCode;
        return this;
    }

    public  Integer getIsCommon(){
        return this.isCommon;
    }

    public  SmsMessageTemplate setIsCommon(Integer isCommon){
        this.isCommon = isCommon;
        return this;
    }

    public  Integer getMsgType(){
        return this.msgType;
    }

    public  SmsMessageTemplate setMsgType(Integer msgType){
        this.msgType = msgType;
        return this;
    }

    public  Integer getTemplateType(){
        return this.templateType;
    }

    public  SmsMessageTemplate setTemplateType(Integer templateType){
        this.templateType = templateType;
        return this;
    }

    public  String getContent(){
        return this.content;
    }

    public  SmsMessageTemplate setContent(String content){
        this.content = content;
        return this;
    }

    public  String getThirdTmpId(){
        return this.thirdTmpId;
    }

    public  SmsMessageTemplate setThirdTmpId(String thirdTmpId){
        this.thirdTmpId = thirdTmpId;
        return this;
    }

    public  String getServicerCode(){
        return this.servicerCode;
    }

    public  SmsMessageTemplate setServicerCode(String servicerCode){
        this.servicerCode = servicerCode;
        return this;
    }

    public  String getReplaceList(){
        return this.replaceList;
    }

    public  SmsMessageTemplate setReplaceList(String replaceList){
        this.replaceList = replaceList;
        return this;
    }

    public  String getDescInfo(){
        return this.descInfo;
    }

    public  SmsMessageTemplate setDescInfo(String descInfo){
        this.descInfo = descInfo;
        return this;
    }

    public  String getType(){
        return this.type;
    }

    public  SmsMessageTemplate setType(String type){
        this.type = type;
        return this;
    }

    public  String getSign(){
        return this.sign;
    }

    public  SmsMessageTemplate setSign(String sign){
        this.sign = sign;
        return this;
    }
}
