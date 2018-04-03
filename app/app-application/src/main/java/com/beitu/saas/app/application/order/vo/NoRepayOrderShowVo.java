package com.beitu.saas.app.application.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class NoRepayOrderShowVo {
    private String orderNo;

    private BigDecimal realCapital;

    public String getOrderNo() {
        return orderNo;
    }

    public NoRepayOrderShowVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public NoRepayOrderShowVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }
}
