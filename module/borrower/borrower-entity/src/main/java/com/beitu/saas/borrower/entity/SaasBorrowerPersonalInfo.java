package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-10
 * Time: 16:49:47.292
 * TableDesc:SAAS借款人个人信息表
 */
public class SaasBorrowerPersonalInfo extends BaseEntity {
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 微信号
     */
    private String wechatCode;
    /**
     * 学历
     */
    private Integer education;
    /**
     * 居住地址
     */
    private String address;
    /**
     * 居住时长
     */
    private String liveDuration;
    /**
     * 婚姻状况
     */
    private Integer maritalStatus;
    /**
     * 芝麻分
     */
    private Integer zmCreditScore;


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasBorrowerPersonalInfo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public SaasBorrowerPersonalInfo setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
        return this;
    }

    public String getWechatCode() {
        return this.wechatCode;
    }

    public SaasBorrowerPersonalInfo setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
        return this;
    }

    public Integer getEducation() {
        return this.education;
    }

    public SaasBorrowerPersonalInfo setEducation(Integer education) {
        this.education = education;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public SaasBorrowerPersonalInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLiveDuration() {
        return this.liveDuration;
    }

    public SaasBorrowerPersonalInfo setLiveDuration(String liveDuration) {
        this.liveDuration = liveDuration;
        return this;
    }

    public Integer getMaritalStatus() {
        return this.maritalStatus;
    }

    public SaasBorrowerPersonalInfo setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public Integer getZmCreditScore() {
        return this.zmCreditScore;
    }

    public SaasBorrowerPersonalInfo setZmCreditScore(Integer zmCreditScore) {
        this.zmCreditScore = zmCreditScore;
        return this;
    }
}
