package com.beitu.saas.credit.domain;
import com.fqgj.common.api.ResponseData;
import java.io.Serializable;
import java.util.Date;
/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.674
*/
public class SaasCreditTongdunVo implements ResponseData,Serializable{

    private Long saasCreditTongdunId;

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

    public Long getSaasCreditTongdunId() {
        return saasCreditTongdunId;
    }

    public void setSaasCreditTongdunId(Long saasCreditTongdunId) {
        this.saasCreditTongdunId = saasCreditTongdunId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  void setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
    }

    public  String getReportId(){
        return this.reportId;
    }

    public  void setReportId(String reportId){
        this.reportId = reportId;
    }

    public  Boolean getSuccess(){
        return this.success;
    }

    public  void setSuccess(Boolean success){
        this.success = success;
    }
}
