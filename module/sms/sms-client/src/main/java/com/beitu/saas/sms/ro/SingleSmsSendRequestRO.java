package com.beitu.saas.sms.ro;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class SingleSmsSendRequestRO implements Serializable {

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
     * 替换参数 短信验证码 replaceParam.put("VERIFY_CODE","1234")
     */
    private Map<String, String> replaceParam;
    /**
     * 类型 0 正常 1 验证码  默认0
     */
    private Integer type = 0;
    /**
     * 发送时间 不必填  默认即时发送
     */
    private Date sendTime;
    /**
     * appId
     */
    private int appId;

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

    public Map<String, String> getReplaceParam() {
        return replaceParam;
    }

    public void setReplaceParam(Map<String, String> replaceParam) {
        this.replaceParam = replaceParam;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
