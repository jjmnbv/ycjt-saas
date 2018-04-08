package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditTongdun;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.674
 */
public class SaasCreditTongdunVo implements ResponseData, Serializable {

    private Long saasCreditTongdunId;

    /**
     * 机构码
     */
    private String merchantCode;
    /**
     * 用户码
     */
    private String borrowerCode;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 用户身份证号码
     */
    private String identityCode;
    /**
     * 同盾流水编号
     */
    private String reportId;
    /**
     * 是否查询成功
     */
    private Boolean success;

    private Date gmtCreate;

    public Long getSaasCreditTongdunId() {
        return saasCreditTongdunId;
    }

    public void setSaasCreditTongdunId(Long saasCreditTongdunId) {
        this.saasCreditTongdunId = saasCreditTongdunId;
    }


    public String getMerchantCode() {
        return this.merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentityCode() {
        return this.identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getReportId() {
        return this.reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public static SaasCreditTongdunVo convertEntityToVO(SaasCreditTongdun saasCreditTongdun) {
        if (saasCreditTongdun == null) {
            return null;
        }
        SaasCreditTongdunVo saasCreditTongdunVo = new SaasCreditTongdunVo();
        BeanUtils.copyProperties(saasCreditTongdun, saasCreditTongdunVo);
        saasCreditTongdunVo.setSaasCreditTongdunId(saasCreditTongdun.getId());
        return saasCreditTongdunVo;
    }

    public static SaasCreditTongdun convertVOToEntity(SaasCreditTongdunVo saasCreditTongdunVo) {
        if (saasCreditTongdunVo == null) {
            return null;
        }
        SaasCreditTongdun saasCreditTongdun = new SaasCreditTongdun();
        BeanUtils.copyProperties(saasCreditTongdunVo, saasCreditTongdun);
        saasCreditTongdun.setId(saasCreditTongdunVo.getSaasCreditTongdunId());
        return saasCreditTongdun;
    }

}
