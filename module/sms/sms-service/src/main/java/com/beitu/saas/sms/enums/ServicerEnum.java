package com.beitu.saas.sms.enums;

public enum ServicerEnum {

    YEGOU("YEGOU"),
    MENGWANG("MENGWANG"),
    JIANZHOU("JIANZHOU"),
    DH3T("DH3T"),
    WEIWANG("WEIWANG"),
    DH3T_VOICE("DH3T_VOICE");

    private String code;

    ServicerEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
