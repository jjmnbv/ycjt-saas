package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.997
 */
public class SaasBorrowerPersonalInfoVo implements ResponseData, Serializable {

    private Long saasBorrowerPersonalInfoId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * QQ
     */
    private String qQ;
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
     * 是否成功
     */
    private Boolean success;

    public Long getSaasBorrowerPersonalInfoId() {
        return saasBorrowerPersonalInfoId;
    }

    public void setSaasBorrowerPersonalInfoId(Long saasBorrowerPersonalInfoId) {
        this.saasBorrowerPersonalInfoId = saasBorrowerPersonalInfoId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getQQ() {
        return this.qQ;
    }

    public void setQQ(String qQ) {
        this.qQ = qQ;
    }

    public Integer getEducation() {
        return this.education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLiveDuration() {
        return this.liveDuration;
    }

    public void setLiveDuration(String liveDuration) {
        this.liveDuration = liveDuration;
    }

    public Integer getMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
