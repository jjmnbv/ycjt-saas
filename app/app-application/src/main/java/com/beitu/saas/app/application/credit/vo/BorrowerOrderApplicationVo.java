package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/4/3 下午4:33
 * @description
 */
@ApiModel(value = "用户申请信息详情")
public class BorrowerOrderApplicationVo implements ResponseData {

    @ApiModelProperty(value = "贷款金额")
    private String realCapital;

    @ApiModelProperty(value = "贷款金额")
    private String totalInterestRatio;

    @ApiModelProperty(value = "借款意图")
    private String borrowPurpose;

    @ApiModelProperty(value = "当前状态")
    private String orderStatus;

    @ApiModelProperty(value = "借款周期")
    private String borrowingDuration;

    @ApiModelProperty(value = "还款方式")
    private String paymentMethod;

    @ApiModelProperty(value = "渠道")
    private String channelName;

    public String getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(String realCapital) {
        this.realCapital = realCapital;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public String getBorrowPurpose() {
        return borrowPurpose;
    }

    public void setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(String borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

}
