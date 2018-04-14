package com.beitu.saas.sms.enums;

public enum TextMsgType {

    CONTENT(0, "普通内容"),
    VERIFY_CODE(1, "验证码");

    private Integer code;

    private String message;

    TextMsgType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
