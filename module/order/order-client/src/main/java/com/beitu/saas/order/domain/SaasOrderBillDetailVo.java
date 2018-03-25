package com.beitu.saas.order.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* User: jungle
* Date: 2018-03-25
* Time: 21:55:45.872
*/
public class SaasOrderBillDetailVo implements ResponseData,Serializable{

    private Long saasOrderBillDetailId;

    /**
    *订单号
    */
    private String orderNumb;
    /**
    *机构CODE
    */
    private String merchantCode;
    /**
    *渠道CODE
    */
    private String channelCode;
    /**
    *借款人CODE
    */
    private String borrowerCode;
    /**
    *借款金额
    */
    private BigDecimal realCapital;
    /**
    *账单应还日
    */
    private Date repaymentDt;
    /**
    *利息
    */
    private BigDecimal interest;
    /**
    *应还金额
    */
    private BigDecimal amount;
    /**
    *上次计息时间
    */
    private Date lastCalculateInterestDt;
    /**
    *当前借款年利率
    */
    private BigDecimal currentInterestRatio;
    /**
    *是否是展期订单
    */
    private Boolean isExtend;
    /**
    *是否已还款
    */
    private Boolean paid;
    /**
    *实际还款金额
    */
    private BigDecimal paidAmount;
    /**
    *抵扣金额
    */
    private BigDecimal deductAmount;
    /**
    *实际还款时间
    */
    private Date actualRepaymentDate;
    /**
    *实际还款日期
    */
    private Date actualRepaymentDt;
    /**
    *订单生成日期
    */
    private Date createdDt;

    public Long getSaasOrderBillDetailId() {
        return saasOrderBillDetailId;
    }

    public void setSaasOrderBillDetailId(Long saasOrderBillDetailId) {
        this.saasOrderBillDetailId = saasOrderBillDetailId;
    }



    public  String getOrderNumb(){
        return this.orderNumb;
    }

    public  void setOrderNumb(String orderNumb){
        this.orderNumb = orderNumb;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  String getChannelCode(){
        return this.channelCode;
    }

    public  void setChannelCode(String channelCode){
        this.channelCode = channelCode;
    }

    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  void setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
    }

    public  BigDecimal getRealCapital(){
        return this.realCapital;
    }

    public  void setRealCapital(BigDecimal realCapital){
        this.realCapital = realCapital;
    }

    public  Date getRepaymentDt(){
        return this.repaymentDt;
    }

    public  void setRepaymentDt(Date repaymentDt){
        this.repaymentDt = repaymentDt;
    }

    public  BigDecimal getInterest(){
        return this.interest;
    }

    public  void setInterest(BigDecimal interest){
        this.interest = interest;
    }

    public  BigDecimal getAmount(){
        return this.amount;
    }

    public  void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public  Date getLastCalculateInterestDt(){
        return this.lastCalculateInterestDt;
    }

    public  void setLastCalculateInterestDt(Date lastCalculateInterestDt){
        this.lastCalculateInterestDt = lastCalculateInterestDt;
    }

    public  BigDecimal getCurrentInterestRatio(){
        return this.currentInterestRatio;
    }

    public  void setCurrentInterestRatio(BigDecimal currentInterestRatio){
        this.currentInterestRatio = currentInterestRatio;
    }

    public  Boolean getIsExtend(){
        return this.isExtend;
    }

    public  void setIsExtend(Boolean isExtend){
        this.isExtend = isExtend;
    }

    public  Boolean getPaid(){
        return this.paid;
    }

    public  void setPaid(Boolean paid){
        this.paid = paid;
    }

    public  BigDecimal getPaidAmount(){
        return this.paidAmount;
    }

    public  void setPaidAmount(BigDecimal paidAmount){
        this.paidAmount = paidAmount;
    }

    public  BigDecimal getDeductAmount(){
        return this.deductAmount;
    }

    public  void setDeductAmount(BigDecimal deductAmount){
        this.deductAmount = deductAmount;
    }

    public  Date getActualRepaymentDate(){
        return this.actualRepaymentDate;
    }

    public  void setActualRepaymentDate(Date actualRepaymentDate){
        this.actualRepaymentDate = actualRepaymentDate;
    }

    public  Date getActualRepaymentDt(){
        return this.actualRepaymentDt;
    }

    public  void setActualRepaymentDt(Date actualRepaymentDt){
        this.actualRepaymentDt = actualRepaymentDt;
    }

    public  Date getCreatedDt(){
        return this.createdDt;
    }

    public  void setCreatedDt(Date createdDt){
        this.createdDt = createdDt;
    }
}
