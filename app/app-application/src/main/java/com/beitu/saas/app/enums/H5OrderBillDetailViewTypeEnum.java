package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/26 下午5:47
 * @description
 */
public enum H5OrderBillDetailViewTypeEnum implements MsgCodeEnum {

    FINISHED(1, "账单已结清"),
    FOR_REIMBURSEMENT(2, "待还款"),
    TO_CONFIRM_EXTEND(3, "展期待确认"),
    OVERDUE(4, "已逾期");

    H5OrderBillDetailViewTypeEnum(Integer code, String msg) {
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