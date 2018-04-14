package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

import java.util.Arrays;

/**
 * @author linanjun
 * @create 2018/3/26 下午5:47
 * @description
 */
public enum H5OrderBillDetailViewTypeEnum implements MsgCodeEnum {

    FINISHED(1, "账单已结清", new Integer[]{501, 502}),
    FOR_REIMBURSEMENT(2, "待还款", new Integer[]{401}),
    TO_CONFIRM_EXTEND(3, "展期待确认", new Integer[]{303, 402}),
    OVERDUE(4, "已逾期", new Integer[]{999});

    H5OrderBillDetailViewTypeEnum(Integer code, String msg, Integer[] orderStatusArray) {
        this.code = code;
        this.msg = msg;
        this.orderStatusArray = orderStatusArray;
    }

    private Integer code;

    private String msg;

    private Integer[] orderStatusArray;

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

    public Integer[] getOrderStatusArray() {
        return orderStatusArray;
    }

    public void setOrderStatusArray(Integer[] orderStatusArray) {
        this.orderStatusArray = orderStatusArray;
    }

    public static H5OrderBillDetailViewTypeEnum getByOrderStatus(Integer orderStatus) {
        for (H5OrderBillDetailViewTypeEnum typeEnum : H5OrderBillDetailViewTypeEnum.values()) {
            if (Arrays.binarySearch(typeEnum.getOrderStatusArray(), orderStatus) > -1) {
                return typeEnum;
            }
        }
        return FOR_REIMBURSEMENT;
    }

}