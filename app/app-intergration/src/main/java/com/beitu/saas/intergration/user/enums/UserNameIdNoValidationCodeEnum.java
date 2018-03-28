package com.beitu.saas.intergration.user.enums;

public enum UserNameIdNoValidationCodeEnum {
    
    MATCH(10001, "一致"),
    MISMATCH(10002, "不一致"),
    RES_ERROR(10003, "优分接口请求错误"),
    OTHER_ERROR(10004, "其他错误");
    
    private Integer code;
    
    private String msg;
    
    UserNameIdNoValidationCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
    
}
