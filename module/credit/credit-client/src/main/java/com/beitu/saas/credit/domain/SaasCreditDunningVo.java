package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.663
 */
public class SaasCreditDunningVo implements ResponseData, Serializable {

    private Long saasCreditDunningId;

    /**
     * 机构码
     */
    private String merchantCode;
    /**
     * 用户码
     */
    private String borrowerCode;
    /**
     * 运营商数据ID
     */
    private Long carrierId;
    /**
     * 电话邦催收数据查询唯一标识
     */
    private String sid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 总记录数
     */
    private Integer totalNum;
    /**
     * 有效数据数
     */
    private Integer effectiveNum;
    /**
     * 催收数据存储地址
     */
    private String url;
    /**
     * 是否查询成功
     */
    private Boolean success;

    public Long getSaasCreditDunningId() {
        return saasCreditDunningId;
    }

    public void setSaasCreditDunningId(Long saasCreditDunningId) {
        this.saasCreditDunningId = saasCreditDunningId;
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

    public Long getCarrierId() {
        return this.carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getEffectiveNum() {
        return this.effectiveNum;
    }

    public void setEffectiveNum(Integer effectiveNum) {
        this.effectiveNum = effectiveNum;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static SaasCreditDunningVo convertEntityToVO(SaasCreditDunning saasCreditDunning) {
        if (saasCreditDunning == null) {
            return null;
        }
        SaasCreditDunningVo saasCreditDunningVo = new SaasCreditDunningVo();
        BeanUtils.copyProperties(saasCreditDunning, saasCreditDunningVo);
        saasCreditDunningVo.setSaasCreditDunningId(saasCreditDunning.getId());
        return saasCreditDunningVo;
    }

    public static SaasCreditDunning convertVOToEntity(SaasCreditDunningVo saasCreditDunningVo) {
        if (saasCreditDunningVo == null) {
            return null;
        }
        SaasCreditDunning saasCreditDunning = new SaasCreditDunning();
        BeanUtils.copyProperties(saasCreditDunningVo, saasCreditDunning);
        saasCreditDunning.setId(saasCreditDunningVo.getSaasCreditDunningId());
        return saasCreditDunning;
    }

}
