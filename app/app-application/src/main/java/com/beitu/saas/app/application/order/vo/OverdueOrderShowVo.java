package com.beitu.saas.app.application.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class OverdueOrderShowVo {
    private String orderNo;

    private BigDecimal realCapital;

    public String getOrderNo() {
        return orderNo;
    }

    public OverdueOrderShowVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public OverdueOrderShowVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }
}
