package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.979
 */
public class SaasBorrowerEmergentContactVo implements ResponseData, Serializable {

    private Long saasBorrowerEmergentContactId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 直系亲属联系人类型
     */
    private String familyType;
    /**
     * 直系亲属联系人输入姓名
     */
    private String familyName;
    /**
     * 直系亲属联系人手机号
     */
    private String familyMobile;
    /**
     * 同事朋友联系人类型
     */
    private String friendType;
    /**
     * 同事朋友联系人输入姓名
     */
    private String friendName;
    /**
     * 同事朋友联系人手机号
     */
    private String friendMobile;
    /**
     * 是否成功
     */
    private Boolean success;

    public Long getSaasBorrowerEmergentContactId() {
        return saasBorrowerEmergentContactId;
    }

    public void setSaasBorrowerEmergentContactId(Long saasBorrowerEmergentContactId) {
        this.saasBorrowerEmergentContactId = saasBorrowerEmergentContactId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getFamilyType() {
        return this.familyType;
    }

    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyMobile() {
        return this.familyMobile;
    }

    public void setFamilyMobile(String familyMobile) {
        this.familyMobile = familyMobile;
    }

    public String getFriendType() {
        return this.friendType;
    }

    public void setFriendType(String friendType) {
        this.friendType = friendType;
    }

    public String getFriendName() {
        return this.friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendMobile() {
        return this.friendMobile;
    }

    public void setFriendMobile(String friendMobile) {
        this.friendMobile = friendMobile;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
