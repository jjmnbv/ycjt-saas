package com.beitu.saas.credit.domain;
import com.fqgj.common.api.ResponseData;
import java.io.Serializable;
import java.util.Date;
/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.607
*/
public class SaasCreditBmpVo implements ResponseData,Serializable{

    private Long saasCreditBmpId;

    /**
    *机构码
    */
    private String merchantCode;
    /**
    *用户码
    */
    private String borrowerCode;
    /**
    *电话邦催收数据查询唯一标识
    */
    private String sid;
    /**
    *电话邦匹配数据存储地址
    */
    private String url;
    /**
    *是否查询成功
    */
    private Boolean success;

    public Long getSaasCreditBmpId() {
        return saasCreditBmpId;
    }

    public void setSaasCreditBmpId(Long saasCreditBmpId) {
        this.saasCreditBmpId = saasCreditBmpId;
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

    public  String getSid(){
        return this.sid;
    }

    public  void setSid(String sid){
        this.sid = sid;
    }

    public  String getUrl(){
        return this.url;
    }

    public  void setUrl(String url){
        this.url = url;
    }

    public  Boolean getSuccess(){
        return this.success;
    }

    public  void setSuccess(Boolean success){
        this.success = success;
    }
}
