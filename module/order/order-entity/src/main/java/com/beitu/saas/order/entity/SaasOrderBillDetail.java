package com.beitu.saas.order.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
* User: jungle
* Date: 2018-03-25
* Time: 21:55:45.864
* TableDesc:SAAS订单详情表
*/
public class SaasOrderBillDetail extends BaseEntity{
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


    public  String getOrderNumb(){
        return this.orderNumb;
    }

    public  SaasOrderBillDetail setOrderNumb(String orderNumb){
        this.orderNumb = orderNumb;
        return this;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasOrderBillDetail setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getChannelCode(){
        return this.channelCode;
    }

    public  SaasOrderBillDetail setChannelCode(String channelCode){
        this.channelCode = channelCode;
        return this;
    }

    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasOrderBillDetail setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  BigDecimal getRealCapital(){
        return this.realCapital;
    }

    public  SaasOrderBillDetail setRealCapital(BigDecimal realCapital){
        this.realCapital = realCapital;
        return this;
    }

    public  Date getRepaymentDt(){
        return this.repaymentDt;
    }

    public  SaasOrderBillDetail setRepaymentDt(Date repaymentDt){
        this.repaymentDt = repaymentDt;
        return this;
    }

    public  BigDecimal getInterest(){
        return this.interest;
    }

    public  SaasOrderBillDetail setInterest(BigDecimal interest){
        this.interest = interest;
        return this;
    }

    public  BigDecimal getAmount(){
        return this.amount;
    }

    public  SaasOrderBillDetail setAmount(BigDecimal amount){
        this.amount = amount;
        return this;
    }

    public  Date getLastCalculateInterestDt(){
        return this.lastCalculateInterestDt;
    }

    public  SaasOrderBillDetail setLastCalculateInterestDt(Date lastCalculateInterestDt){
        this.lastCalculateInterestDt = lastCalculateInterestDt;
        return this;
    }

    public  BigDecimal getCurrentInterestRatio(){
        return this.currentInterestRatio;
    }

    public  SaasOrderBillDetail setCurrentInterestRatio(BigDecimal currentInterestRatio){
        this.currentInterestRatio = currentInterestRatio;
        return this;
    }

    public  Boolean getIsExtend(){
        return this.isExtend;
    }

    public  SaasOrderBillDetail setIsExtend(Boolean isExtend){
        this.isExtend = isExtend;
        return this;
    }

    public  Boolean getPaid(){
        return this.paid;
    }

    public  SaasOrderBillDetail setPaid(Boolean paid){
        this.paid = paid;
        return this;
    }

    public  BigDecimal getPaidAmount(){
        return this.paidAmount;
    }

    public  SaasOrderBillDetail setPaidAmount(BigDecimal paidAmount){
        this.paidAmount = paidAmount;
        return this;
    }

    public  BigDecimal getDeductAmount(){
        return this.deductAmount;
    }

    public  SaasOrderBillDetail setDeductAmount(BigDecimal deductAmount){
        this.deductAmount = deductAmount;
        return this;
    }

    public  Date getActualRepaymentDate(){
        return this.actualRepaymentDate;
    }

    public  SaasOrderBillDetail setActualRepaymentDate(Date actualRepaymentDate){
        this.actualRepaymentDate = actualRepaymentDate;
        return this;
    }

    public  Date getActualRepaymentDt(){
        return this.actualRepaymentDt;
    }

    public  SaasOrderBillDetail setActualRepaymentDt(Date actualRepaymentDt){
        this.actualRepaymentDt = actualRepaymentDt;
        return this;
    }

    public  Date getCreatedDt(){
        return this.createdDt;
    }

    public  SaasOrderBillDetail setCreatedDt(Date createdDt){
        this.createdDt = createdDt;
        return this;
    }
}
