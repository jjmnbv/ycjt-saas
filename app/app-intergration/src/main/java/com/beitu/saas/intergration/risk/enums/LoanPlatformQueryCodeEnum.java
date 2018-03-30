package com.beitu.saas.intergration.risk.enums;

public enum LoanPlatformQueryCodeEnum {
    
    SUCCESS(0000, "成功"),
    PARAM_ERROR(1000, "输入参数有误"),
    RESPONSE_ERROR(1001, "接口响应有误");
    
    private Integer code;
    
    private String msg;
    
    LoanPlatformQueryCodeEnum(Integer code, String msg) {
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
