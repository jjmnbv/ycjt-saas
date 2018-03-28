package com.beitu.saas.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class NoRepayOrderVo {
    private String orderNo;

    private BigDecimal realCapital;

    public String getOrderNo() {
        return orderNo;
    }

    public NoRepayOrderVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public NoRepayOrderVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }
}
