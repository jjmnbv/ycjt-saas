package com.beitu.saas.order.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
* User: jungle
* Date: 2018-03-23
* Time: 15:18:54.737
* TableDesc:SAAS订单表
*/
public class SaasOrder extends BaseEntity{
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
    *借款年利率
    */
    private BigDecimal totalInterestRatio;
    /**
    *借款总利息
    */
    private BigDecimal totalInterestFee;
    /**
    *逾期利率
    */
    private BigDecimal lateFeeRatio;
    /**
    *借款意图
    */
    private String borrowPurpose;
    /**
    *账单应还日
    */
    private Date repaymentDt;
    /**
    *订单申请时间
    */
    private Date createdDt;
    /**
    *借款协议URL地址
    */
    private String termUrl;
    /**
    *是否是展期订单
    */
    private Boolean isExtend;
    /**
    *展期关联订单CODE
    */
    private String relationOrderCode;
    /**
    *订单状态
    */
    private Integer orderStatus;
    /**
    *初审员
    */
    private String primaryReviewerCode;
    /**
    *复审员
    */
    private String finalReviewerCode;
    /**
    *放款人
    */
    private String loanLenderCode;
    /**
    *备注
    */
    private String remark;
    /**
    *
    */
    private Long version;


    public  String getOrderNumb(){
        return this.orderNumb;
    }

    public  SaasOrder setOrderNumb(String orderNumb){
        this.orderNumb = orderNumb;
        return this;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasOrder setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getChannelCode(){
        return this.channelCode;
    }

    public  SaasOrder setChannelCode(String channelCode){
        this.channelCode = channelCode;
        return this;
    }

    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasOrder setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  BigDecimal getRealCapital(){
        return this.realCapital;
    }

    public  SaasOrder setRealCapital(BigDecimal realCapital){
        this.realCapital = realCapital;
        return this;
    }

    public  BigDecimal getTotalInterestRatio(){
        return this.totalInterestRatio;
    }

    public  SaasOrder setTotalInterestRatio(BigDecimal totalInterestRatio){
        this.totalInterestRatio = totalInterestRatio;
        return this;
    }

    public  BigDecimal getTotalInterestFee(){
        return this.totalInterestFee;
    }

    public  SaasOrder setTotalInterestFee(BigDecimal totalInterestFee){
        this.totalInterestFee = totalInterestFee;
        return this;
    }

    public  BigDecimal getLateFeeRatio(){
        return this.lateFeeRatio;
    }

    public  SaasOrder setLateFeeRatio(BigDecimal lateFeeRatio){
        this.lateFeeRatio = lateFeeRatio;
        return this;
    }

    public  String getBorrowPurpose(){
        return this.borrowPurpose;
    }

    public  SaasOrder setBorrowPurpose(String borrowPurpose){
        this.borrowPurpose = borrowPurpose;
        return this;
    }

    public  Date getRepaymentDt(){
        return this.repaymentDt;
    }

    public  SaasOrder setRepaymentDt(Date repaymentDt){
        this.repaymentDt = repaymentDt;
        return this;
    }

    public  Date getCreatedDt(){
        return this.createdDt;
    }

    public  SaasOrder setCreatedDt(Date createdDt){
        this.createdDt = createdDt;
        return this;
    }

    public  String getTermUrl(){
        return this.termUrl;
    }

    public  SaasOrder setTermUrl(String termUrl){
        this.termUrl = termUrl;
        return this;
    }

    public  Boolean getIsExtend(){
        return this.isExtend;
    }

    public  SaasOrder setIsExtend(Boolean isExtend){
        this.isExtend = isExtend;
        return this;
    }

    public  String getRelationOrderCode(){
        return this.relationOrderCode;
    }

    public  SaasOrder setRelationOrderCode(String relationOrderCode){
        this.relationOrderCode = relationOrderCode;
        return this;
    }

    public  Integer getOrderStatus(){
        return this.orderStatus;
    }

    public  SaasOrder setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
        return this;
    }

    public  String getPrimaryReviewerCode(){
        return this.primaryReviewerCode;
    }

    public  SaasOrder setPrimaryReviewerCode(String primaryReviewerCode){
        this.primaryReviewerCode = primaryReviewerCode;
        return this;
    }

    public  String getFinalReviewerCode(){
        return this.finalReviewerCode;
    }

    public  SaasOrder setFinalReviewerCode(String finalReviewerCode){
        this.finalReviewerCode = finalReviewerCode;
        return this;
    }

    public  String getLoanLenderCode(){
        return this.loanLenderCode;
    }

    public  SaasOrder setLoanLenderCode(String loanLenderCode){
        this.loanLenderCode = loanLenderCode;
        return this;
    }

    public  String getRemark(){
        return this.remark;
    }

    public  SaasOrder setRemark(String remark){
        this.remark = remark;
        return this;
    }

    public  Long getVersion(){
        return this.version;
    }

    public  SaasOrder setVersion(Long version){
        this.version = version;
        return this;
    }
}
