package com.beitu.saas.order.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;
import org.hibernate.criterion.Order;

/**
 * @author linanjun
 * @create 2018/3/26 下午5:07
 * @description
 */
public enum OrderStatusEnum implements MsgCodeEnum {

    SUBMIT_PRELIMINARY_REVIEW(101, "提交初审"),
    IN_PRELIMINARY_REVIEWER(102, "初审中"),
    PRELIMINARY_REVIEWER_GET_ORDER(103, "初审已领单"),
    PRELIMINARY_REVIEWER_REJECT(104, "初审驳回"),
    PRELIMINARY_REVIEWER_REFUSE(105, "初审拒绝"),
    SUBMIT_FINAL_REVIEW(201, "提交复审"),
    IN_FINAL_REVIEWER(202, "复审中"),
    FINAL_REVIEWER_GET_ORDER(203, "复审已领单"),
    FINAL_REVIEWER_REJECT(204, "复审驳回"),
    FINAL_REVIEWER_REFUSE(205, "复审拒绝"),
    SUBMIT_LOAN_LENDER(301, "待放款"),
    LOAN_LENDER_REFUSE(302, "放款拒绝"),
    TO_CONFIRM_RECEIPT(303, "待确认收款"),
    FOR_REIMBURSEMENT(401, "待还款"),
    TO_CONFIRM_EXTEND(402, "展期待确认"),
    IN_EXTEND(403, "展期中"),
    HAS_BEEN_PAYMENT(501, "已还款"),
    HAS_BEEN_DESTROY(502, "已核销"),
    OVERDUE(999, "已逾期");

    OrderStatusEnum(Integer code, String msg) {
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

    public static OrderStatusEnum getByCode(Integer code) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.getCode().equals(code)) {
                return orderStatusEnum;
            }
        }
        return null;
    }

}