package com.beitu.saas.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class DashboardOrderVo {
    private String orderNo;

    private String borrowCode;

    private BigDecimal realCapital;

    public String getBorrowCode() {
        return borrowCode;
    }

    public DashboardOrderVo setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public DashboardOrderVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public DashboardOrderVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }
}
