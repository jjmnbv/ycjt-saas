package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-04-16
* Time: 21:04:02.111
* TableDesc:优分多头信息表
*/
public class SaasMultiDebitEntity extends BaseEntity{
    /**
    *借款人CODE
    */
    private String borrowerCode;
    /**
    *手机号
    */
    private String mobile;
    /**
    *用户身份证号码
    */
    private String identityCode;
    /**
    *客户风险评分
    */
    private Integer score;
    /**
    *贷款失败率
    */
    private BigDecimal failRate;
    /**
    *多头数据存储地址
    */
    private String url;
    /**
    *失效日期
    */
    private Date expiredDt;
    /**
    *渠道状态(0-有效,1-失效)
    */
    private Integer status;


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasMultiDebitEntity setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getMobile(){
        return this.mobile;
    }

    public  SaasMultiDebitEntity setMobile(String mobile){
        this.mobile = mobile;
        return this;
    }

    public  String getIdentityCode(){
        return this.identityCode;
    }

    public  SaasMultiDebitEntity setIdentityCode(String identityCode){
        this.identityCode = identityCode;
        return this;
    }

    public  Integer getScore(){
        return this.score;
    }

    public  SaasMultiDebitEntity setScore(Integer score){
        this.score = score;
        return this;
    }

    public  BigDecimal getFailRate(){
        return this.failRate;
    }

    public  SaasMultiDebitEntity setFailRate(BigDecimal failRate){
        this.failRate = failRate;
        return this;
    }

    public  String getUrl(){
        return this.url;
    }

    public  SaasMultiDebitEntity setUrl(String url){
        this.url = url;
        return this;
    }

    public  Date getExpiredDt(){
        return this.expiredDt;
    }

    public  SaasMultiDebitEntity setExpiredDt(Date expiredDt){
        this.expiredDt = expiredDt;
        return this;
    }

    public  Integer getStatus(){
        return this.status;
    }

    public  SaasMultiDebitEntity setStatus(Integer status){
        this.status = status;
        return this;
    }
}
