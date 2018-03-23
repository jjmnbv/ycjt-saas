package com.beitu.saas.sms.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author linanjun
 * @create 2018/3/22 下午9:54
 * @description
 */
public enum SmsErrorCodeEnum implements ErrorCodeEnum {

    REPEAT_REQUEST(70001, "请求过于频繁，请稍候再试", HttpStatus.OK),
    VERIFY_CODE_FAILURE(70002, "验证码失效，请重试", HttpStatus.OK),
    INPUT_WRONG_VERIFY_CODE(70003, "验证码输入错误", HttpStatus.OK),
    VERIFYCODE_TYPE_NOT_EXIST(70004, "请求验证码类型不存在", HttpStatus.OK),;


    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    SmsErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}