package com.beitu.saas.intergration.user.dto;


import com.beitu.saas.intergration.user.enums.UserMultiDebitCodeEnum;
import com.fqgj.common.utils.StringUtils;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/16
 * Time: 下午3:32
 */
public class UserMultiDebitDto implements Serializable {
    private static final long serialVersionUID = -8564920145652112526L;
    private Integer code;

    private String msg;

    private String result;

    public UserMultiDebitDto(UserMultiDebitCodeEnum codeEnum, String msg) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        if (StringUtils.isNotEmpty(msg)) {
            this.msg = msg;
        }
    }

    public UserMultiDebitDto(Integer code, String msg, String result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public UserMultiDebitDto setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public UserMultiDebitDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getResult() {
        return result;
    }

    public UserMultiDebitDto setResult(String result) {
        this.result = result;
        return this;
    }
}
