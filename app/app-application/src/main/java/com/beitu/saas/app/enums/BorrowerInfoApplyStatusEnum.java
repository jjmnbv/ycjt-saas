package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/23 下午2:30
 * @description
 */
public enum BorrowerInfoApplyStatusEnum implements MsgCodeEnum {

    INCOMPLETE(1, "未填写"),
    SAVED(2, "已保存"),
    FINISHED(3, "已完成");

    BorrowerInfoApplyStatusEnum(Integer code, String msg) {
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