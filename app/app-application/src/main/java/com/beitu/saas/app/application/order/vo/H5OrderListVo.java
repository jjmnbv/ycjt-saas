package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;

/**
 * @author linanjun
 * @create 2018/3/25 下午2:02
 * @description
 */
public class H5OrderListVo implements ResponseData {

    private String orderNumb;

    private String amount;

    private String repaymentDt;

    private Integer orderStatus;

    private Integer viewType;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }
}
