package com.beitu.saas.intergration.risk.enums;

public enum LoanPlatformCrawlingCodeEnum {
    
    SUCCESS(1000, "成功"),
    PARAM_ERROR(1001, "输入参数有误");
    
    private Integer code;
    
    private String msg;
    
    LoanPlatformCrawlingCodeEnum(Integer code, String msg) {
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
