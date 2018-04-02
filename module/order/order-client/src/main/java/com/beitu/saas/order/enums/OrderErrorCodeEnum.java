package com.beitu.saas.order.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author linanjun
 * @create 2018/3/26 下午5:57
 * @description
 */
public enum OrderErrorCodeEnum implements ErrorCodeEnum {

    ERROR_ORDER_NUMB(60001, "请核对订单号", HttpStatus.OK),
    ILLEGAL_OPERATION_ORDER_STATUS(60002, "非法操作订单状态，请刷新后重试", HttpStatus.OK),
    ORDER_STATUS_UPDATE_FAILURE(60003, "订单状态更新失败", HttpStatus.OK),
    ORDER_BEING_SINGLE(60004, "订单已被他人领单", HttpStatus.OK),
    NO_PERMISSION_OPERATE_ORDER(60005, "没有该订单操作权限", HttpStatus.OK),
    ILLEGAL_REPAYMENTDATE(60006, "应还日期非法", HttpStatus.OK),
    ORDER_FAILURE(60007, "订单已失效", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    OrderErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public OrderErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public OrderErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public OrderErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

}