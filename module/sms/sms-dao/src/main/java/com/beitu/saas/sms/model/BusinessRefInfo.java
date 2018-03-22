package com.beitu.saas.sms.model;

import com.fqgj.common.entity.BaseEntity;

public class BusinessRefInfo extends BaseEntity {

    /**
     * app标识
     */
    private Long appId;

    private String appCode;

    private String appSign;

    private Long bizFunctionId;

    private Long appServicerId;

    private Long messageTemplateId;

    private Long sendRuleId;

    private Integer rank;

    /**
     * 是否有效：0-不予发送，1-发送短信
     */
    private Integer sendFlag;

    private String bizCode;

    private String bizName;

    private String servicerCode;

    private String linkInfo;

    private String msgType;
    /**
     * 模板来源：1-本地模板；2-第三方模板
     */
    private String templateType;

    private String content;

    private String replaceList;

    private String descInfo;

    private String sendRuleName;

    private String ruleContent;

    private Integer level;

    private String type;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getBizFunctionId() {
        return bizFunctionId;
    }

    public void setBizFunctionId(Long bizFunctionId) {
        this.bizFunctionId = bizFunctionId;
    }

    public Long getAppServicerId() {
        return appServicerId;
    }

    public void setAppServicerId(Long appServicerId) {
        this.appServicerId = appServicerId;
    }

    public Long getMessageTemplateId() {
        return messageTemplateId;
    }

    public void setMessageTemplateId(Long messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    public Long getSendRuleId() {
        return sendRuleId;
    }

    public void setSendRuleId(Long sendRuleId) {
        this.sendRuleId = sendRuleId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getServicerCode() {
        return servicerCode;
    }

    public void setServicerCode(String servicerCode) {
        this.servicerCode = servicerCode;
    }

    public String getLinkInfo() {
        return linkInfo;
    }

    public void setLinkInfo(String linkInfo) {
        this.linkInfo = linkInfo;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplaceList() {
        return replaceList;
    }

    public void setReplaceList(String replaceList) {
        this.replaceList = replaceList;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public String getSendRuleName() {
        return sendRuleName;
    }

    public void setSendRuleName(String sendRuleName) {
        this.sendRuleName = sendRuleName;
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    public String getAppSign() {
        return appSign;
    }

    public void setAppSign(String appSign) {
        this.appSign = appSign;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
