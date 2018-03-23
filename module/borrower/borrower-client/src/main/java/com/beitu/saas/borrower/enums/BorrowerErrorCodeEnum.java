package com.beitu.saas.borrower.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/2/26
 * Time: 下午3:53
 * 用户模块的错误代码归类
 * <p>
 */
public enum BorrowerErrorCodeEnum implements ErrorCodeEnum {

    MOBILE_EXIST(20001, "该手机号已注册", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;


    BorrowerErrorCodeEnum(Integer code, String desc, HttpStatus httpStatus) {
        this.code = code;
        this.msg = desc;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public BorrowerErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public BorrowerErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BorrowerErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
