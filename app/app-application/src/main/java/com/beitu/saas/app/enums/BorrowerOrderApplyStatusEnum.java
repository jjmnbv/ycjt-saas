package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/22 下午8:35
 * @description
 */
public enum BorrowerOrderApplyStatusEnum implements MsgCodeEnum {

    NO_SUBMIT(1, "", "未提交"),
    REVIEWING(2, "正在申请", "审核中"),
    REFUSE(3, "申请驳回", "申请驳回");

    BorrowerOrderApplyStatusEnum(Integer code, String msg, String remark) {
        this.code = code;
        this.msg = msg;
        this.remark = remark;
    }

    private Integer code;

    private String msg;

    private String remark;

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
