package com.beitu.saas.borrower.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.995
* TableDesc:SAAS借款人个人信息表
*/
public class SaasBorrowerPersonalInfo extends BaseEntity{
    /**
    *借款人CODE
    */
    private String borrowerCode;
    /**
    *QQ
    */
    private String qQ;
    /**
    *学历
    */
    private Integer education;
    /**
    *居住地址
    */
    private String address;
    /**
    *居住时长
    */
    private String liveDuration;
    /**
    *婚姻状况
    */
    private Integer maritalStatus;
    /**
    *是否成功
    */
    private Boolean success;


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasBorrowerPersonalInfo setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getQQ(){
        return this.qQ;
    }

    public  SaasBorrowerPersonalInfo setQQ(String qQ){
        this.qQ = qQ;
        return this;
    }

    public  Integer getEducation(){
        return this.education;
    }

    public  SaasBorrowerPersonalInfo setEducation(Integer education){
        this.education = education;
        return this;
    }

    public  String getAddress(){
        return this.address;
    }

    public  SaasBorrowerPersonalInfo setAddress(String address){
        this.address = address;
        return this;
    }

    public  String getLiveDuration(){
        return this.liveDuration;
    }

    public  SaasBorrowerPersonalInfo setLiveDuration(String liveDuration){
        this.liveDuration = liveDuration;
        return this;
    }

    public  Integer getMaritalStatus(){
        return this.maritalStatus;
    }

    public  SaasBorrowerPersonalInfo setMaritalStatus(Integer maritalStatus){
        this.maritalStatus = maritalStatus;
        return this;
    }

    public  Boolean getSuccess(){
        return this.success;
    }

    public  SaasBorrowerPersonalInfo setSuccess(Boolean success){
        this.success = success;
        return this;
    }
}
