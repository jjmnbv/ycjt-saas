package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:44
 * @description
 */
public class OrderApplicationListVo implements ResponseData {

    @ApiModelProperty(value = "申请日期")
    private String createdDt;

    @ApiModelProperty(value = "申请金额")
    private String realCapital;

    @ApiModelProperty(value = "借款周期")
    private String borrowingDuration;

    @ApiModelProperty(value = "姓名")
    private String borrowerName;

    @ApiModelProperty(value = "手机号")
    private String borrowerMobile;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "渠道")
    private String channelName;

    @ApiModelProperty(value = "还款方式")
    private String repaymentMethod;

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public String getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(String realCapital) {
        this.realCapital = realCapital;
    }

    public String getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(String borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerMobile() {
        return borrowerMobile;
    }

    public void setBorrowerMobile(String borrowerMobile) {
        this.borrowerMobile = borrowerMobile;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }
}