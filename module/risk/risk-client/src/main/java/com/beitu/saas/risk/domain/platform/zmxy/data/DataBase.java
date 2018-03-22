package com.beitu.saas.risk.domain.platform.zmxy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 11:55
 */
public class DataBase implements Serializable {
    @JsonProperty(value = "biz_no")
    private String bizNo;
    @JsonProperty(value = "success")
    private String success;
    @JsonProperty(value = "error_message")
    private String errorMessage;
    @JsonProperty(value = "error_code")
    private String errorCode;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
