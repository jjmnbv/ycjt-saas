package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/26 下午9:59
 * @description
 */
public class OrderDetailVo implements ResponseData {

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

    @ApiModelProperty(value = "借款金额")
    private String realCapital;

    @ApiModelProperty(value = "借款天数")
    private Integer borrowingDuration;

    @ApiModelProperty(value = "借款年利率")
    private String totalInterestRatio;

    @ApiModelProperty(value = "到期日期")
    private String repaymentDt;

    @ApiModelProperty(value = "到期应还")
    private String amount;

    @ApiModelProperty(value = "借款用途")
    private String borrowPurpose;

    @ApiModelProperty(value = "借款人CODE")
    private String borrowerCode;

    @ApiModelProperty(value = "借款人姓名")
    private String borrowerName;

    @ApiModelProperty(value = "借款人身份证号")
    private String borrowerIdentityCode;

    @ApiModelProperty(value = "借款合同")
    private String termUrl;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "展期信息")
    private List<ExtendOrderDetailVo> extendOrderDetailVos;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(String realCapital) {
        this.realCapital = realCapital;
    }

    public Integer getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(Integer borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBorrowPurpose() {
        return borrowPurpose;
    }

    public void setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
    }

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerIdentityCode() {
        return borrowerIdentityCode;
    }

    public void setBorrowerIdentityCode(String borrowerIdentityCode) {
        this.borrowerIdentityCode = borrowerIdentityCode;
    }

    public String getTermUrl() {
        return termUrl;
    }

    public void setTermUrl(String termUrl) {
        this.termUrl = termUrl;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<ExtendOrderDetailVo> getExtendOrderDetailVos() {
        return extendOrderDetailVos;
    }

    public void setExtendOrderDetailVos(List<ExtendOrderDetailVo> extendOrderDetailVos) {
        this.extendOrderDetailVos = extendOrderDetailVos;
    }
}