package com.beitu.saas.sms.ro;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class VoiceMsgRequestRO implements Serializable {

	private static final long serialVersionUID = 4970030336025334447L;
    /**
     * 手机号【必填】
     */
    private String phone;
    /**
     * 业务码【必填】
     */
    private String bizCode;
    /**
     * 验证码
     */
    private String verifyCode;
    /**
     * 发送时间 不必填  默认即时发送
     */
    private Date sendTime;
    /**
     * appId
     */
    private int appId;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
