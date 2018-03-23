package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.889
 * TableDesc:SAAS借款人实名信息表
 */
public class SaasBorrowerRealInfo extends BaseEntity {
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 用户实名
     */
    private String name;
    /**
     * 用户身份证号码
     */
    private String identityCode;
    /**
     * 用户性别
     */
    private Integer gender;
    /**
     * 用户籍贯
     */
    private String nativePlace;


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasBorrowerRealInfo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public SaasBorrowerRealInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentityCode() {
        return this.identityCode;
    }

    public SaasBorrowerRealInfo setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public Integer getGender() {
        return this.gender;
    }

    public SaasBorrowerRealInfo setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public String getNativePlace() {
        return this.nativePlace;
    }

    public SaasBorrowerRealInfo setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
        return this;
    }
}
