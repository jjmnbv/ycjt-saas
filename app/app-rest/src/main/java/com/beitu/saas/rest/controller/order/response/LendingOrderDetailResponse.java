package com.beitu.saas.rest.controller.order.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

/**
 * @author linanjun
 * @create 2018/3/21 下午3:59
 * @description
 */
@ApiModel(value = "订单风控信息")
public class LendingOrderDetailResponse implements ResponseData {

    private String orderNumb;

    private String borrowerName;

    private String capital;

    private String totalInterestRatio;

    private String borrowingDuration;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public String getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(String borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

}
