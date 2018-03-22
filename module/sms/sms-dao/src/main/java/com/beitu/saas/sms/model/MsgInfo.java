package com.beitu.saas.sms.model;

import java.io.Serializable;
import java.util.Date;

public class MsgInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6280935430905623849L;
    /**
     * 消息id
     */
	private Long msgId;
    /**
     * 业务bizId
     */
	private String bizMsgId;
    /**
     * 第三方消息id
     */
	private String thirdMsgId;
    /**
     * 消息模板id
     */
	private Integer msgTmpId;
    /**
     * 1、短信 2、语音 3、邮件 4、站内push
     */
	private Integer msgType;
    /**
     * 业务功能码
     */
	private String bizCode;
    /**
     * 服务商码
     */
	private String servicerCode;
    /**
     * 收消息账号
     */
	private String receiver;
    /**
     * 消息内容
     */
	private String content;
    /**
     * app签名
     */
	private String appSign;
    /**
     * 发送类型（单发、群发）
     */
	private Integer sendType;
    /**
     * 时效性类型（即时、延时、定时）
     */
	private Integer ageingType;
    /**
     * 消息状态（状态：0 待发送 1 已发送 2 发送失败 3 发送成功已确认）
     */
	private Integer status;

    /**
     * 消息发送时间
     */
	private Date sendTime;
    /**
     * 拓展信息
     */
	private String extInfo;
    /**
     * 创建人
     */
	private String createPerson;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新人
     */
	private String updatePerson;
    /**
     * 更新时间
     */
	private Date updateTime;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getBizMsgId() {
        return bizMsgId;
    }

    public void setBizMsgId(String bizMsgId) {
        this.bizMsgId = bizMsgId;
    }

    public String getThirdMsgId() {
        return thirdMsgId;
    }

    public void setThirdMsgId(String thirdMsgId) {
        this.thirdMsgId = thirdMsgId;
    }

    public String getBizCode() {
        return bizCode;
    }

    public String getServicerCode() {
        return servicerCode;
    }

    public void setServicerCode(String servicerCode) {
        this.servicerCode = servicerCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getAgeingType() {
        return ageingType;
    }

    public void setAgeingType(Integer ageingType) {
        this.ageingType = ageingType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getMsgTmpId() {
        return msgTmpId;
    }

    public void setMsgTmpId(Integer msgTmpId) {
        this.msgTmpId = msgTmpId;
    }

    public String getAppSign() {
        return appSign;
    }

    public void setAppSign(String appSign) {
        this.appSign = appSign;
    }
}
