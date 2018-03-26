package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-26
 * Time: 11:33:49.719
 * TableDesc:SAAS借款人工作信息表
 */
public class SaasBorrowerWorkInfo extends BaseEntity {
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
    private String career;
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


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasBorrowerWorkInfo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public SaasBorrowerWorkInfo setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
        return this;
    }

    public String getCareer() {
        return this.career;
    }

    public SaasBorrowerWorkInfo setCareer(String career) {
        this.career = career;
        return this;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public SaasBorrowerWorkInfo setSalary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public Integer getPayDay() {
        return this.payDay;
    }

    public SaasBorrowerWorkInfo setPayDay(Integer payDay) {
        this.payDay = payDay;
        return this;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public SaasBorrowerWorkInfo setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyDetailAddress() {
        return this.companyDetailAddress;
    }

    public SaasBorrowerWorkInfo setCompanyDetailAddress(String companyDetailAddress) {
        this.companyDetailAddress = companyDetailAddress;
        return this;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public SaasBorrowerWorkInfo setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
