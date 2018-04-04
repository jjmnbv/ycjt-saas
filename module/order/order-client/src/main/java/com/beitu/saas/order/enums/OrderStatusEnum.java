package com.beitu.saas.order.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;
import org.hibernate.criterion.Order;

/**
 * @author linanjun
 * @create 2018/3/26 下午5:07
 * @description
 */
public enum OrderStatusEnum implements MsgCodeEnum {

    SUBMIT_PRELIMINARY_REVIEW(101, "提交初审", new Integer[]{104}, Boolean.TRUE),
    IN_PRELIMINARY_REVIEWER(102, "初审中", new Integer[]{101}, Boolean.FALSE),
    PRELIMINARY_REVIEWER_REJECT(104, "初审驳回", new Integer[]{102}, Boolean.TRUE),
    PRELIMINARY_REVIEWER_REFUSE(105, "初审拒绝", new Integer[]{102}, Boolean.TRUE),
    SUBMIT_FINAL_REVIEW(201, "提交复审", new Integer[]{102, 204}, Boolean.TRUE),
    IN_FINAL_REVIEWER(202, "复审中", new Integer[]{201}, Boolean.FALSE),
    FINAL_REVIEWER_REJECT(204, "复审驳回", new Integer[]{202}, Boolean.TRUE),
    FINAL_REVIEWER_REFUSE(205, "复审拒绝", new Integer[]{202}, Boolean.TRUE),
    SUBMIT_LOAN_LENDER(301, "待放款", new Integer[]{202, 203}, Boolean.TRUE),
    LOAN_LENDER_REFUSE(302, "放款拒绝", new Integer[]{301}, Boolean.TRUE),
    TO_CONFIRM_RECEIPT(303, "待确认收款", new Integer[]{301}, Boolean.TRUE),
    FOR_REIMBURSEMENT(401, "待还款", new Integer[]{301, 303, 402}, Boolean.TRUE),
    TO_CONFIRM_EXTEND(402, "展期待确认", new Integer[]{401}, Boolean.TRUE),
    IN_EXTEND(403, "展期中", new Integer[]{401}, Boolean.TRUE),
    HAS_BEEN_PAYMENT(501, "已还款", new Integer[]{401}, Boolean.TRUE),
    HAS_BEEN_DESTROY(502, "已核销", new Integer[]{401}, Boolean.TRUE),
    OVERDUE(999, "已逾期", null, Boolean.FALSE);

    private Integer code;

    private String msg;

    private Integer[] codeArray;

    private Boolean needForcedToUpdate;

    private OrderStatusEnum(Integer code, String msg, Integer[] codeArray, Boolean needForcedToUpdate) {
        this.code = code;
        this.msg = msg;
        this.codeArray = codeArray;
        this.needForcedToUpdate = needForcedToUpdate;
    }

    public static OrderStatusEnum getEnumByCode(Integer code) {
        for (OrderStatusEnum item : OrderStatusEnum.values()) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public OrderStatusEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public OrderStatusEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer[] getCodeArray() {
        return codeArray;
    }

    public OrderStatusEnum setCodeArray(Integer[] codeArray) {
        this.codeArray = codeArray;
        return this;
    }

    public Boolean getNeedForcedToUpdate() {
        return needForcedToUpdate;
    }

    public void setNeedForcedToUpdate(Boolean needForcedToUpdate) {
        this.needForcedToUpdate = needForcedToUpdate;
    }
}