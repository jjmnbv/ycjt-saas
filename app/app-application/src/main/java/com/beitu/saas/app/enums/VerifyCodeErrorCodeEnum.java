package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:11
 * @description
 */
public enum VerifyCodeErrorCodeEnum implements ErrorCodeEnum {

    VERIFYCODE_NOT_CORRECT(60001, "请输入正确验证码", HttpStatus.OK),
    VERIFYCODE_TYPE_NOT_EXIST(60002, "请求验证码类型不存在", HttpStatus.OK),
    VERIFYCODE_IMG_EMPTY(60003, "图片验证码为空", HttpStatus.OK),
    VERIFYCODE_IMG_NOT_CORRECT(60004, "请输入正确的图片验证码", HttpStatus.OK),
    NOT_MOBILE(60005, "请输入正确的手机号码", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    VerifyCodeErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public VerifyCodeErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public VerifyCodeErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public VerifyCodeErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}