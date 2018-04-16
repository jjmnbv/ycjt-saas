package com.beitu.saas.intergration.user.enums;

public enum UserMultiDebitCodeEnum {

    QUERY_SUCCESS(2001, "查询成功"),
    COMMIT_ERROR(2002, "提交请求异常"),
    QUERY_ERROR(2003, "查询接口请求异常"),
    OTHER_ERROR(2004, "其他异常");

    private Integer code;

    private String msg;

    UserMultiDebitCodeEnum(Integer code, String msg) {
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
