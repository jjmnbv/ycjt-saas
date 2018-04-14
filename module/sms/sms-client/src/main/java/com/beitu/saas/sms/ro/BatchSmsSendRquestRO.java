package com.beitu.saas.sms.ro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BatchSmsSendRquestRO implements Serializable {

	private static final long serialVersionUID = 1456651449388155606L;
    /**
     * 批量发送内容
     */
    private List<SmsMsgBatchContentRO> contents;
    /**
     * 业务码
     */
    private String bizCode;
    /**
     * 发送时间
     */
    private Date sendTime;

    private int appId;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public List<SmsMsgBatchContentRO> getContents() {
        return contents;
    }

    public void setContents(List<SmsMsgBatchContentRO> contents) {
        this.contents = contents;
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
