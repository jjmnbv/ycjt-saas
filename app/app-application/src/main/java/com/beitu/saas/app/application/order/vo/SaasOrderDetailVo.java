package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author linanjun
 * @create 2018/4/4 下午1:05
 * @description
 */
public class SaasOrderDetailVo implements ResponseData {

    @ApiModelProperty(value = "期数")
    private Integer period;

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

    @ApiModelProperty(value = "金额")
    private String capital;

    @ApiModelProperty(value = "应还金额")
    private String amount;

    @ApiModelProperty(value = "借款年利率")
    private String totalInterestRatio;

    @ApiModelProperty(value = "借款利息")
    private String interest;

    @ApiModelProperty(value = "借款天数")
    private Integer borrowingDuration;

    @ApiModelProperty(value = "申请日期")
    private String createdDate;

    @ApiModelProperty(value = "应还日期")
    private String repaymentDate;

    @ApiModelProperty(value = "逾期天数")
    private Integer overdueDuration;

    @ApiModelProperty(value = "手续费")
    private Integer serviceFee;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Integer getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(Integer borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Integer getOverdueDuration() {
        return overdueDuration;
    }

    public void setOverdueDuration(Integer overdueDuration) {
        this.overdueDuration = overdueDuration;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
