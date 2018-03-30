package com.beitu.saas.intergration.user.enums;

public enum UserNameIdNoValidationCodeEnum {
    
    MATCH(1001, "一致"),
    MISMATCH(1002, "不一致"),
    RES_ERROR(1003, "优分接口请求异常"),
    OTHER_ERROR(1004, "其他异常");
    
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
