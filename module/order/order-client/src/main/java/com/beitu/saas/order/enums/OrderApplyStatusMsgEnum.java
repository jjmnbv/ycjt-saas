package com.beitu.saas.order.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/4/3 下午4:54
 * @description
 */
public enum OrderApplyStatusMsgEnum implements MsgCodeEnum {

    NO_SUBMIT(1, "未提交"),
    SUBMIT(2, "提交"),
    REVIEWER_REJECT(3, "审核驳回");

    private Integer code;

    private String msg;

    OrderApplyStatusMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return null;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
