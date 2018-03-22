package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:47.023
 */
public class SaasBorrowerWorkInfoVo implements ResponseData, Serializable {

    private Long saasBorrowerWorkInfoId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
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

    public Integer getCareerType() {
        return careerType;
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
