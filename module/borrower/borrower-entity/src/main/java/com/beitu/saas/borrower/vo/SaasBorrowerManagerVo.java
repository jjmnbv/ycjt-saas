package com.beitu.saas.borrower.vo;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: watson
 * Date: 2018-04-12
 * Time: 20:25:59.840
 */
public class SaasBorrowerManagerVo implements ResponseData, Serializable {

    private String borrowerCode;
    private String name;
    private String mobile;
    private String gender;
    private String age;
    private String identityCode;
    private String nativePlace;
    private String zmCreditScore;
    private String gmtCreate;
    private String channelCode;
    private String channelName;


    public String getBorrowerCode() {
        return borrowerCode;
    }

    public SaasBorrowerManagerVo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public SaasBorrowerManagerVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public SaasBorrowerManagerVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaasBorrowerManagerVo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getAge() {
        return age;
    }

    public SaasBorrowerManagerVo setAge(String age) {
        this.age = age;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public SaasBorrowerManagerVo setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public SaasBorrowerManagerVo setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
        return this;
    }

    public String getZmCreditScore() {
        return zmCreditScore;
    }

    public SaasBorrowerManagerVo setZmCreditScore(String zmCreditScore) {
        this.zmCreditScore = zmCreditScore;
        return this;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public SaasBorrowerManagerVo setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public SaasBorrowerManagerVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasBorrowerManagerVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }
}
