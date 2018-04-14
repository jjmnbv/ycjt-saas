package com.beitu.saas.sms.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-02-28
* Time: 15:46:53.783
* TableDesc:发送规则表
*/
public class SmsSendRule extends BaseEntity{
    /**
    *appId
    */
    private Long appId;
    /**
    *发送规则名称
    */
    private String sendRuleName;
    /**
    *规则内容， json格式，支持多条规则
    */
    private String ruleContent;
    /**
    *是否有效
    */
    private Integer status;
    /**
    *备注
    */
    private String remark;


    public  Long getAppId(){
        return this.appId;
    }

    public  SmsSendRule setAppId(Long appId){
        this.appId = appId;
        return this;
    }

    public  String getSendRuleName(){
        return this.sendRuleName;
    }

    public  SmsSendRule setSendRuleName(String sendRuleName){
        this.sendRuleName = sendRuleName;
        return this;
    }

    public  String getRuleContent(){
        return this.ruleContent;
    }

    public  SmsSendRule setRuleContent(String ruleContent){
        this.ruleContent = ruleContent;
        return this;
    }

    public  Integer getStatus(){
        return this.status;
    }

    public  SmsSendRule setStatus(Integer status){
        this.status = status;
        return this;
    }

    public  String getRemark(){
        return this.remark;
    }

    public  SmsSendRule setRemark(String remark){
        this.remark = remark;
        return this;
    }
}
