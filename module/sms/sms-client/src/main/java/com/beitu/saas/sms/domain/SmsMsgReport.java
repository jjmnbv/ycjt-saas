package com.beitu.saas.sms.domain;

import java.io.Serializable;

public class SmsMsgReport implements Serializable{
    /** TODO: detail description  */
    private static final long serialVersionUID = 8159930053274536411L;
    /**
     * 三方消息流水号
     */
    private String thirdMsgId;
    /**
     * 状态
     */
    private Boolean status;

    /**
     * 备注字段
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getThirdMsgId() {
        return thirdMsgId;
    }

    public void setThirdMsgId(String thirdMsgId) {
        this.thirdMsgId = thirdMsgId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
