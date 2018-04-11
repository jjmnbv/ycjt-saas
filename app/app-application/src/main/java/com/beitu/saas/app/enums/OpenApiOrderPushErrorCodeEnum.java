package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:11
 * @description
 */
public enum OpenApiOrderPushErrorCodeEnum implements ErrorCodeEnum {
    
    ORDER_RECEIVE_FAIL(10000, "接口进件失败", HttpStatus.OK),
    DATA_PARSE_ERROR(10001, "数据解析失败", HttpStatus.OK),
    NO_MATCHED_MERCHANT(20001, "未找到匹配推单对象机构", HttpStatus.OK),
    USER_BASIC_INFO_MISS(20002, "用户基础信息缺失", HttpStatus.OK),
    USER_ORDER_INFO_MISS(20003, "用户订单信息缺失", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    OpenApiOrderPushErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public OpenApiOrderPushErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public OpenApiOrderPushErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public OpenApiOrderPushErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}