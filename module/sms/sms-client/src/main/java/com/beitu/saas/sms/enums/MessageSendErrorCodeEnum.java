package com.beitu.saas.sms.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Watson Qiu
 * Date: 2017/03/01
 * Time: 下午4:08
 */
    public enum MessageSendErrorCodeEnum implements ErrorCodeEnum{

    SEND_SUCCESS(70001, "短信发送成功", HttpStatus.OK),
    SEND_FAILED(70002, "短信发送失败", HttpStatus.BAD_REQUEST),
    SEND_OVER_MAX(70003, "已超过每天发送最大次数", HttpStatus.BAD_REQUEST),
    BLACK_PHONE_NUMBER(70004, "您的号码在黑名单里，请联系运营商", HttpStatus.BAD_REQUEST),
    NULL_PHONE_NUMBER(70005, "手机号码为空", HttpStatus.BAD_REQUEST),
    UPDATE_FAILED(70006, "更新短信失败", HttpStatus.BAD_REQUEST),
    ADD_FAILED(70007, "添加短信失败", HttpStatus.BAD_REQUEST),
    BATCH_ADD_FAILED(70008, "批量添加短信失败", HttpStatus.BAD_REQUEST),
    DELETE_FAILED(70009, "删除短信失败", HttpStatus.BAD_REQUEST),
    QUERY_FAILED(700010, "查询短信失败", HttpStatus.BAD_REQUEST),
    MESSAGE_NOT_EXIST(700011, "短信不存在", HttpStatus.BAD_REQUEST),
    MESSAGE_CALL_SUCCESS(700012, "语音验证码拨打成功", HttpStatus.BAD_REQUEST),
    MESSAGE_CALL_FAILED(700013, "语音验证码拨打失败", HttpStatus.BAD_REQUEST);


    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    MessageSendErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
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
