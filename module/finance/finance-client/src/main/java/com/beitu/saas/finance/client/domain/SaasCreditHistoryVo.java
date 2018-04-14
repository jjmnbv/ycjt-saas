package com.beitu.saas.finance.client.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:10.938
*/
public class SaasCreditHistoryVo implements ResponseData,Serializable{

    private Long saasCreditHistoryId;

    /**
    *机构码
    */
    private String merchantCode;
    /**
    *余额操作类型，0：支出，1：收入
    */
    private Integer opType;
    /**
    *点券
    */
    private Long credit;
    /**
    *操作后点券
    */
    private Long currentCredit;
    /**
    *备注信息
    */
    private String comment;
    /**
    *操作人
    */
    private String opName;

    public Long getSaasCreditHistoryId() {
        return saasCreditHistoryId;
    }

    public void setSaasCreditHistoryId(Long saasCreditHistoryId) {
        this.saasCreditHistoryId = saasCreditHistoryId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  Integer getOpType(){
        return this.opType;
    }

    public  void setOpType(Integer opType){
        this.opType = opType;
    }

    public  Long getCredit(){
        return this.credit;
    }

    public  void setCredit(Long credit){
        this.credit = credit;
    }

    public  Long getCurrentCredit(){
        return this.currentCredit;
    }

    public  void setCurrentCredit(Long currentCredit){
        this.currentCredit = currentCredit;
    }

    public  String getComment(){
        return this.comment;
    }

    public  void setComment(String comment){
        this.comment = comment;
    }

    public  String getOpName(){
        return this.opName;
    }

    public  void setOpName(String opName){
        this.opName = opName;
    }
}
