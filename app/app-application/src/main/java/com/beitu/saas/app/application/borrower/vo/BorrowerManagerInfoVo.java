package com.beitu.saas.app.application.borrower.vo;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: watson
 * Date: 2018-04-12
 * Time: 20:25:59.840
 */
public class BorrowerManagerInfoVo implements ResponseData, Serializable {

    private String borrowerCode;
    private String name;
    private String mobile;
    private String gender;
    private Integer age;
    private String identityCode;
    private String nativePlace;
    private Integer zmCreditScore;
    private String gmtCreate;
    private String channelCode;
    private String channelName;


    public String getBorrowerCode() {
        return borrowerCode;
    }

    public BorrowerManagerInfoVo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public BorrowerManagerInfoVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public BorrowerManagerInfoVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public BorrowerManagerInfoVo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public BorrowerManagerInfoVo setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public BorrowerManagerInfoVo setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public BorrowerManagerInfoVo setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
        return this;
    }

    public Integer getZmCreditScore() {
        return zmCreditScore;
    }

    public BorrowerManagerInfoVo setZmCreditScore(Integer zmCreditScore) {
        this.zmCreditScore = zmCreditScore;
        return this;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public BorrowerManagerInfoVo setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public BorrowerManagerInfoVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public BorrowerManagerInfoVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }
}
