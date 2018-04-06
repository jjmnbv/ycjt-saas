package com.beitu.saas.credit.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.672
* TableDesc:SAAS同盾信用记录查询表
*/
public class SaasCreditTongdun extends BaseEntity{
    /**
    *机构码
    */
    private String merchantCode;
    /**
    *用户码
    */
    private String borrowerCode;
    /**
    * 同盾流水编号
    */
    private String reportId;
    /**
    *是否查询成功
    */
    private Boolean success;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasCreditTongdun setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasCreditTongdun setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getReportId(){
        return this.reportId;
    }

    public  SaasCreditTongdun setReportId(String reportId){
        this.reportId = reportId;
        return this;
    }

    public  Boolean getSuccess(){
        return this.success;
    }

    public  SaasCreditTongdun setSuccess(Boolean success){
        this.success = success;
        return this;
    }
}
