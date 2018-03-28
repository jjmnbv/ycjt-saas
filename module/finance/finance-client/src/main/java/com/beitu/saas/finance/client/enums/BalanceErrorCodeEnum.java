package com.beitu.saas.finance.client.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author xiaochong
 * @create 2018/3/28 下午5:02
 * @description
 */
public enum BalanceErrorCodeEnum implements ErrorCodeEnum {

    LACK_OF_BALANCE(93001, "余额不足", HttpStatus.OK),
    BALANCE_UPDATE_ERROR(93002, "余额更新失败", HttpStatus.OK),
    AMOUNT_ERROR(93003, "余额非法", HttpStatus.OK),
    PROHIBITION_OF_OPERATION(93004, "您有逾期的借款，请还款后再操作。", HttpStatus.OK);


    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    BalanceErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public BalanceErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public BalanceErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BalanceErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
