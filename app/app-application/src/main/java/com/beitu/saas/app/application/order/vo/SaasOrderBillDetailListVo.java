package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/28 上午11:38
 * @description
 */
public class SaasOrderBillDetailListVo implements ResponseData {

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

    @ApiModelProperty(value = "借款人姓名")
    private String borrowerName;

    @ApiModelProperty(value = "借款人手机号")
    private String borrowerMobile;

    @ApiModelProperty(value = "金额")
    private String capital;

    @ApiModelProperty(value = "应还金额")
    private String amount;

    @ApiModelProperty(value = "申请日期")
    private String createdDate;

    @ApiModelProperty(value = "应还日期")
    private String repaymentDate;

    @ApiModelProperty(value = "是否展期订单")
    private Boolean extend;

    @ApiModelProperty(value = "逾期天数")
    private Integer overdueDuration;

    @ApiModelProperty(value = "放款备注")
    private String loanLendRemark;

    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;


    @ApiModelProperty(value = "是否能委托催收")
    private boolean entrustCollect;

    public boolean isEntrustCollect() {
        return entrustCollect;
    }

    public SaasOrderBillDetailListVo setEntrustCollect(boolean entrustCollect) {
        this.entrustCollect = entrustCollect;
        return this;
    }

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

    public String getBorrowerMobile() {
        return borrowerMobile;
    }

    public void setBorrowerMobile(String borrowerMobile) {
        this.borrowerMobile = borrowerMobile;
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

    public Boolean getExtend() {
        return extend;
    }

    public void setExtend(Boolean extend) {
        this.extend = extend;
    }

    public Integer getOverdueDuration() {
        return overdueDuration;
    }

    public void setOverdueDuration(Integer overdueDuration) {
        this.overdueDuration = overdueDuration;
    }

    public String getLoanLendRemark() {
        return loanLendRemark;
    }

    public void setLoanLendRemark(String loanLendRemark) {
        this.loanLendRemark = loanLendRemark;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
