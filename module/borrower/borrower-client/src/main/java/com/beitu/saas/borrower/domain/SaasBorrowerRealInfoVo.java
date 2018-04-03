package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.892
 */
public class SaasBorrowerRealInfoVo implements ResponseData, Serializable {

    private Long saasBorrowerRealInfoId;

    /**
     * 机构CODE
     */
    private String merchantCode;
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

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Long getSaasBorrowerRealInfoId() {
        return saasBorrowerRealInfoId;
    }

    public void setSaasBorrowerRealInfoId(Long saasBorrowerRealInfoId) {
        this.saasBorrowerRealInfoId = saasBorrowerRealInfoId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCode() {
        return this.identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNativePlace() {
        return this.nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public static SaasBorrowerRealInfoVo convertEntityToVO(SaasBorrowerRealInfo saasBorrowerRealInfo) {
        if (saasBorrowerRealInfo == null) {
            return null;
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = new SaasBorrowerRealInfoVo();
        BeanUtils.copyProperties(saasBorrowerRealInfo, saasBorrowerRealInfoVo);
        saasBorrowerRealInfoVo.setSaasBorrowerRealInfoId(saasBorrowerRealInfo.getId());
        return saasBorrowerRealInfoVo;
    }

    public static SaasBorrowerRealInfo convertVOToEntity(SaasBorrowerRealInfoVo saasBorrowerRealInfoVo) {
        if (saasBorrowerRealInfoVo == null) {
            return null;
        }
        SaasBorrowerRealInfo saasBorrowerRealInfo = new SaasBorrowerRealInfo();
        BeanUtils.copyProperties(saasBorrowerRealInfoVo, saasBorrowerRealInfo);
        saasBorrowerRealInfo.setId(saasBorrowerRealInfoVo.getSaasBorrowerRealInfoId());
        return saasBorrowerRealInfo;
    }

}
