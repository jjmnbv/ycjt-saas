package com.beitu.saas.credit.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.util.Date;

/**
* User: xiaochong
* Date: 2018-04-17
* Time: 17:27:58.914
*/
public class SaasGxbEbVo implements ResponseData,Serializable{

    private Long saasGxbEbId;

    /**
    *借款人code
    */
    private String borrowerCode;
    /**
    *本次查询的token
    */
    private String taskToken;
    /**
    *爬取结果的url
    */
    private String jsonUrl;

    private Date gmtCreate;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getSaasGxbEbId() {
        return saasGxbEbId;
    }

    public void setSaasGxbEbId(Long saasGxbEbId) {
        this.saasGxbEbId = saasGxbEbId;
    }



    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  void setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
    }

    public  String getTaskToken(){
        return this.taskToken;
    }

    public  void setTaskToken(String taskToken){
        this.taskToken = taskToken;
    }

    public  String getJsonUrl(){
        return this.jsonUrl;
    }

    public  void setJsonUrl(String jsonUrl){
        this.jsonUrl = jsonUrl;
    }
}
