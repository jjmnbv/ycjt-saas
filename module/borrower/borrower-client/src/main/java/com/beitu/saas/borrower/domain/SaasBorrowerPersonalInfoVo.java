package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerPersonalInfo;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.885
 */
public class SaasBorrowerPersonalInfoVo implements ResponseData, Serializable {

    private Long saasBorrowerPersonalInfoId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * QQ
     */
    private String qq;
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

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public static SaasBorrowerPersonalInfoVo convertEntityToVO(SaasBorrowerPersonalInfo saasBorrowerPersonalInfo) {
        if (saasBorrowerPersonalInfo == null) {
            return null;
        }
        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = new SaasBorrowerPersonalInfoVo();
        BeanUtils.copyProperties(saasBorrowerPersonalInfo, saasBorrowerPersonalInfoVo);
        saasBorrowerPersonalInfoVo.setSaasBorrowerPersonalInfoId(saasBorrowerPersonalInfo.getId());
        return saasBorrowerPersonalInfoVo;
    }

    public static SaasBorrowerPersonalInfo convertVOToEntity(SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo) {
        if (saasBorrowerPersonalInfoVo == null) {
            return null;
        }
        SaasBorrowerPersonalInfo saasBorrowerPersonalInfo = new SaasBorrowerPersonalInfo();
        BeanUtils.copyProperties(saasBorrowerPersonalInfoVo, saasBorrowerPersonalInfo);
        saasBorrowerPersonalInfo.setId(saasBorrowerPersonalInfoVo.getSaasBorrowerPersonalInfoId());
        return saasBorrowerPersonalInfo;
    }

}
