package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/25 下午5:37
 * @description
 */
public enum MaritalStatusMsgCodeEnum implements MsgCodeEnum {

    MARRIED(1, "已婚"),
    UN_MARRIED(2, "未婚");

    MaritalStatusMsgCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

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

    public static MaritalStatusMsgCodeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (MaritalStatusMsgCodeEnum maritalStatusMsgCodeEnum : MaritalStatusMsgCodeEnum.values()) {
            if (maritalStatusMsgCodeEnum.getCode().equals(code)) {
                return maritalStatusMsgCodeEnum;
            }
        }
        return null;
    }

}