package com.fqgj.youjie.auth.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author linanjun
 * @create 2018/1/5 下午5:42
 * @description
 */
public enum AdminErrorCodeEnum implements ErrorCodeEnum {

    HAVE_NOT_PERMISSION(10001, "没有此操作权限", HttpStatus.OK);


    private Integer code;

    private String msg;

    private HttpStatus httpStatus;


    AdminErrorCodeEnum(Integer code, String desc, HttpStatus httpStatus) {
        this.code = code;
        this.msg = desc;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public AdminErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public AdminErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public AdminErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
