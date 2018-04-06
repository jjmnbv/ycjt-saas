package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.638
* TableDesc:运营商报告基本资料表
*/
public class SaasCreditCarrierBase extends BaseEntity{
    /**
    *运营商报告查询表ID
    */
    private Long recordId;
    /**
    *运营商名称
    */
    private String carrierType;
    /**
    *手机号
    */
    private String mobile;
    /**
    *姓名
    */
    private String name;
    /**
    *身份证号码
    */
    private String identityNo;
    /**
    *余额
    */
    private BigDecimal balance;
    /**
    *归属省份
    */
    private String province;
    /**
    *套餐名称
    */
    private String planName;
    /**
    *入网时间
    */
    private Date registerTime;


    public  Long getRecordId(){
        return this.recordId;
    }

    public  SaasCreditCarrierBase setRecordId(Long recordId){
        this.recordId = recordId;
        return this;
    }

    public  String getCarrierType(){
        return this.carrierType;
    }

    public  SaasCreditCarrierBase setCarrierType(String carrierType){
        this.carrierType = carrierType;
        return this;
    }

    public  String getMobile(){
        return this.mobile;
    }

    public  SaasCreditCarrierBase setMobile(String mobile){
        this.mobile = mobile;
        return this;
    }

    public  String getName(){
        return this.name;
    }

    public  SaasCreditCarrierBase setName(String name){
        this.name = name;
        return this;
    }

    public  String getIdentityNo(){
        return this.identityNo;
    }

    public  SaasCreditCarrierBase setIdentityNo(String identityNo){
        this.identityNo = identityNo;
        return this;
    }

    public  BigDecimal getBalance(){
        return this.balance;
    }

    public  SaasCreditCarrierBase setBalance(BigDecimal balance){
        this.balance = balance;
        return this;
    }

    public  String getProvince(){
        return this.province;
    }

    public  SaasCreditCarrierBase setProvince(String province){
        this.province = province;
        return this;
    }

    public  String getPlanName(){
        return this.planName;
    }

    public  SaasCreditCarrierBase setPlanName(String planName){
        this.planName = planName;
        return this;
    }

    public  Date getRegisterTime(){
        return this.registerTime;
    }

    public  SaasCreditCarrierBase setRegisterTime(Date registerTime){
        this.registerTime = registerTime;
        return this;
    }
}
