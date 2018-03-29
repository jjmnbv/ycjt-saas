package com.beitu.saas.auth.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author xiaochong
 * @create 2018/3/29 下午12:15
 * @description
 */
public enum AdminErrorEnum implements ErrorCodeEnum {
    MOBILE_EXIST(50001, "该手机号已注册", HttpStatus.OK),;

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    AdminErrorEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
