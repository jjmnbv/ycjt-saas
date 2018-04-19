package com.beitu.saas.credit.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-04-17
* Time: 17:28:00.146
* TableDesc:公信宝电商爬虫表
*/
public class SaasGxbEb extends BaseEntity {
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


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public SaasGxbEb setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getTaskToken(){
        return this.taskToken;
    }

    public SaasGxbEb setTaskToken(String taskToken){
        this.taskToken = taskToken;
        return this;
    }

    public  String getJsonUrl(){
        return this.jsonUrl;
    }

    public SaasGxbEb setJsonUrl(String jsonUrl){
        this.jsonUrl = jsonUrl;
        return this;
    }
}
