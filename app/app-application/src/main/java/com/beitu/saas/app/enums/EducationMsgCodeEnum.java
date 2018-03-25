package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/25 下午5:31
 * @description
 */
public enum EducationMsgCodeEnum implements MsgCodeEnum {

    UNDER_HIGH_SCHOOL(1, "高中以下"),
    HIGH_SCHOOL(2, "高中"),
    TECHNICAL(3, "中专"),
    JUNIOR(4, "大专"),
    BACHELOR(5, "本科"),
    MASTER(6, "硕士"),
    DOCTOR(7, "博士");

    EducationMsgCodeEnum(Integer code, String msg) {
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

}