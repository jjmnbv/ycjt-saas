package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerCarrier;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.850
 */
public class SaasBorrowerCarrierVo implements ResponseData, Serializable {

    private Long saasBorrowerCarrierId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 运营商数据存储地址
     */
    private String url;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 过期时间
     */
    private Date expireDate;
    /**
     * 是否成功
     */
    private Boolean success;

    public Long getSaasBorrowerCarrierId() {
        return saasBorrowerCarrierId;
    }

    public void setSaasBorrowerCarrierId(Long saasBorrowerCarrierId) {
        this.saasBorrowerCarrierId = saasBorrowerCarrierId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static SaasBorrowerCarrierVo convertEntityToVO(SaasBorrowerCarrier saasBorrowerCarrier) {
        if (saasBorrowerCarrier == null) {
            return null;
        }
        SaasBorrowerCarrierVo saasBorrowerCarrierVo = new SaasBorrowerCarrierVo();
        BeanUtils.copyProperties(saasBorrowerCarrier, saasBorrowerCarrierVo);
        saasBorrowerCarrierVo.setSaasBorrowerCarrierId(saasBorrowerCarrier.getId());
        return saasBorrowerCarrierVo;
    }

    public static SaasBorrowerCarrier convertVOToEntity(SaasBorrowerCarrierVo saasBorrowerCarrierVo) {
        if (saasBorrowerCarrierVo == null) {
            return null;
        }
        SaasBorrowerCarrier saasBorrowerCarrier = new SaasBorrowerCarrier();
        BeanUtils.copyProperties(saasBorrowerCarrierVo, saasBorrowerCarrier);
        saasBorrowerCarrier.setId(saasBorrowerCarrierVo.getSaasBorrowerCarrierId());
        return saasBorrowerCarrier;
    }

}
