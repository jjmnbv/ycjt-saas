package com.beitu.saas.sms.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-02-28
* Time: 15:46:53.753
* TableDesc:发送关系映射表
*/
public class SmsBusinessRef extends BaseEntity{
    /**
    *app标识
    */
    private Long appId;
    /**
    *业务场景 ID
    */
    private Long bizFunctionId;
    /**
    *服务通道 ID, 多个服务商按优先级顺序调用
    */
    private Long appServicerId;
    /**
    *所发短信对应的短信模板
    */
    private Long messageTemplateId;
    /**
    *发送规则
    */
    private Long sendRuleId;
    /**
    *
    */
    private Long businessTypeId;
    /**
    *优先级
    */
    private Integer rank;
    /**
    *是否有效：0-不予发送，1-发送短信
    */
    private Integer sendFlag;


    public  Long getAppId(){
        return this.appId;
    }

    public  SmsBusinessRef setAppId(Long appId){
        this.appId = appId;
        return this;
    }

    public  Long getBizFunctionId(){
        return this.bizFunctionId;
    }

    public  SmsBusinessRef setBizFunctionId(Long bizFunctionId){
        this.bizFunctionId = bizFunctionId;
        return this;
    }

    public  Long getAppServicerId(){
        return this.appServicerId;
    }

    public  SmsBusinessRef setAppServicerId(Long appServicerId){
        this.appServicerId = appServicerId;
        return this;
    }

    public  Long getMessageTemplateId(){
        return this.messageTemplateId;
    }

    public  SmsBusinessRef setMessageTemplateId(Long messageTemplateId){
        this.messageTemplateId = messageTemplateId;
        return this;
    }

    public  Long getSendRuleId(){
        return this.sendRuleId;
    }

    public  SmsBusinessRef setSendRuleId(Long sendRuleId){
        this.sendRuleId = sendRuleId;
        return this;
    }

    public  Long getBusinessTypeId(){
        return this.businessTypeId;
    }

    public  SmsBusinessRef setBusinessTypeId(Long businessTypeId){
        this.businessTypeId = businessTypeId;
        return this;
    }

    public  Integer getRank(){
        return this.rank;
    }

    public  SmsBusinessRef setRank(Integer rank){
        this.rank = rank;
        return this;
    }

    public  Integer getSendFlag(){
        return this.sendFlag;
    }

    public  SmsBusinessRef setSendFlag(Integer sendFlag){
        this.sendFlag = sendFlag;
        return this;
    }
}
