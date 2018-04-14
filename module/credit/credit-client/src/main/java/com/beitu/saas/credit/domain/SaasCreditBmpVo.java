package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditBmp;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.607
 */
public class SaasCreditBmpVo implements ResponseData, Serializable {

    private Long saasCreditBmpId;

    /**
     * 机构码
     */
    private String merchantCode;
    /**
     * 用户码
     */
    private String borrowerCode;
    /**
     * 电话邦催收数据查询唯一标识
     */
    private String sid;
    /**
     * 电话邦匹配数据存储地址
     */
    private String url;
    /**
     * 是否查询成功
     */
    private Boolean success;

    public Long getSaasCreditBmpId() {
        return saasCreditBmpId;
    }

    public void setSaasCreditBmpId(Long saasCreditBmpId) {
        this.saasCreditBmpId = saasCreditBmpId;
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

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public static SaasCreditBmpVo convertEntityToVO(SaasCreditBmp saasCreditBmp) {
        if (saasCreditBmp == null) {
            return null;
        }
        SaasCreditBmpVo saasCreditBmpVo = new SaasCreditBmpVo();
        BeanUtils.copyProperties(saasCreditBmp, saasCreditBmpVo);
        saasCreditBmpVo.setSaasCreditBmpId(saasCreditBmp.getId());
        return saasCreditBmpVo;
    }

    public static SaasCreditBmp convertVOToEntity(SaasCreditBmpVo saasCreditBmpVo) {
        if (saasCreditBmpVo == null) {
            return null;
        }
        SaasCreditBmp saasCreditBmp = new SaasCreditBmp();
        BeanUtils.copyProperties(saasCreditBmpVo, saasCreditBmp);
        saasCreditBmp.setId(saasCreditBmpVo.getSaasCreditBmpId());
        return saasCreditBmp;
    }

}
