package com.beitu.saas.intergration.risk.enums;

public enum LoanPlatformQueryCodeEnum {
    
    SUCCESS(1000, "成功"),
    PARAM_ERROR(1001, "输入参数有误"),
    RESPONSE_ERROR(1002, "接口响应有误"),
    RESULT_ERROR(1003, "接口结果错误");
    
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
