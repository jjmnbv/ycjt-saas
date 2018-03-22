package com.beitu.saas.app.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fqgj.common.api.enums.ErrorCodeEnum;


@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY
)
public class ApiResponse {
    private Integer code;
    private String msg;

    public ApiResponse() {
        this.code = 0;
        this.msg = "SUCCESS";
    }

    public ApiResponse(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    public ApiResponse(String msg) {
        this.code = 0;
        this.msg = msg;
    }

    public ApiResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
