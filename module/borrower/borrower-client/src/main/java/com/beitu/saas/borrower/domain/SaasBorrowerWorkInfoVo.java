package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.906
 */
public class SaasBorrowerWorkInfoVo implements ResponseData, Serializable {

    private Long saasBorrowerWorkInfoId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 职业
     */
    private Integer careerType;
    /**
     * 月收入
     */
    private Integer salary;
    /**
     * 发薪日
     */
    private Integer payDay;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司详细地址
     */
    private String companyDetailAddress;
    /**
     * 是否成功
     */
    private Boolean success;

    public Long getSaasBorrowerWorkInfoId() {
        return saasBorrowerWorkInfoId;
    }

    public void setSaasBorrowerWorkInfoId(Long saasBorrowerWorkInfoId) {
        this.saasBorrowerWorkInfoId = saasBorrowerWorkInfoId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public Integer getCareerType() {
        return this.careerType;
    }

    public void setCareerType(Integer careerType) {
        this.careerType = careerType;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getPayDay() {
        return this.payDay;
    }

    public void setPayDay(Integer payDay) {
        this.payDay = payDay;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDetailAddress() {
        return this.companyDetailAddress;
    }

    public void setCompanyDetailAddress(String companyDetailAddress) {
        this.companyDetailAddress = companyDetailAddress;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
