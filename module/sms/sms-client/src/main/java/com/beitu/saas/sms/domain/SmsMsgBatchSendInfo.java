package com.beitu.saas.sms.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class SmsMsgBatchSendInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5602243687882301826L;
	/**
     * 手机号
     */
	@JSONField(name="mobile")
    private String phone;
    /**
     * 发送内容
     */
    private String content;

    public SmsMsgBatchSendInfo() {
    }

    public SmsMsgBatchSendInfo(String phone, String content) {
        this.phone = phone;
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
