package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author linanjun
 * @create 2018/3/25 下午5:37
 * @description
 */
public enum MaritalStatusMsgCodeEnum implements MsgCodeEnum {

    NOT_MARRIED(0, "未婚"),
    MARRIED_AND_PROCREATED(1, "已婚已育"),
    MARRIED_AND_NOT_PROCREATED(2, "已婚未育"),
    OTHER(3, "其他");

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

    public static MaritalStatusMsgCodeEnum getByDesc(String desc) {
        if (StringUtils.isEmpty(desc)) {
            return null;
        }
        for (MaritalStatusMsgCodeEnum maritalStatusMsgCodeEnum : MaritalStatusMsgCodeEnum.values()) {
            if (Objects.equals(maritalStatusMsgCodeEnum.getMsg(), desc)) {
                return maritalStatusMsgCodeEnum;
            }
        }
        return null;
    }

}