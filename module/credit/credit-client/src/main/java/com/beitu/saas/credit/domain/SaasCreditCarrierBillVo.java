package com.beitu.saas.credit.domain;
import com.fqgj.common.api.ResponseData;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.648
*/
public class SaasCreditCarrierBillVo implements ResponseData,Serializable{

    private Long saasCreditCarrierBillId;

    /**
    *运营商报告查询表ID
    */
    private Long recordId;
    /**
    *账单时间
    */
    private Date billDate;
    /**
    *月基本费
    */
    private BigDecimal baseFee;
    /**
    *月消费
    */
    private BigDecimal totalFee;
    /**
    *呼出次数
    */
    private Integer callingTime;
    /**
    *呼入次数
    */
    private Integer calledTime;
    /**
    *呼出时长（分）
    */
    private Integer callingDuration;
    /**
    *呼入时长（分）
    */
    private Integer calledDuration;

    public Long getSaasCreditCarrierBillId() {
        return saasCreditCarrierBillId;
    }

    public void setSaasCreditCarrierBillId(Long saasCreditCarrierBillId) {
        this.saasCreditCarrierBillId = saasCreditCarrierBillId;
    }



    public  Long getRecordId(){
        return this.recordId;
    }

    public  void setRecordId(Long recordId){
        this.recordId = recordId;
    }

    public  Date getBillDate(){
        return this.billDate;
    }

    public  void setBillDate(Date billDate){
        this.billDate = billDate;
    }

    public  BigDecimal getBaseFee(){
        return this.baseFee;
    }

    public  void setBaseFee(BigDecimal baseFee){
        this.baseFee = baseFee;
    }

    public  BigDecimal getTotalFee(){
        return this.totalFee;
    }

    public  void setTotalFee(BigDecimal totalFee){
        this.totalFee = totalFee;
    }

    public  Integer getCallingTime(){
        return this.callingTime;
    }

    public  void setCallingTime(Integer callingTime){
        this.callingTime = callingTime;
    }

    public  Integer getCalledTime(){
        return this.calledTime;
    }

    public  void setCalledTime(Integer calledTime){
        this.calledTime = calledTime;
    }

    public  Integer getCallingDuration(){
        return this.callingDuration;
    }

    public  void setCallingDuration(Integer callingDuration){
        this.callingDuration = callingDuration;
    }

    public  Integer getCalledDuration(){
        return this.calledDuration;
    }

    public  void setCalledDuration(Integer calledDuration){
        this.calledDuration = calledDuration;
    }
}
