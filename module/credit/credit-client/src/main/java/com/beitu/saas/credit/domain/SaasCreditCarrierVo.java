package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.629
 */
public class SaasCreditCarrierVo implements ResponseData, Serializable {

    private Long saasCreditCarrierId;

    /**
     * 机构码
     */
    private String merchantCode;
    /**
     * 用户码
     */
    private String borrowerCode;
    /**
     * 运营商记录获取来源 1 : 51运营商 2 : 同盾运营商
     */
    private Integer type;
    /**
     * 运营商数据存储地址
     */
    private String url;
    /**
     * 是否查询成功
     */
    private Boolean success;

    public Long getSaasCreditCarrierId() {
        return saasCreditCarrierId;
    }

    public void setSaasCreditCarrierId(Long saasCreditCarrierId) {
        this.saasCreditCarrierId = saasCreditCarrierId;
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

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public static SaasCreditCarrierVo convertEntityToVO(SaasCreditCarrier saasCreditCarrier) {
        if (saasCreditCarrier == null) {
            return null;
        }
        SaasCreditCarrierVo saasCreditCarrierVo = new SaasCreditCarrierVo();
        BeanUtils.copyProperties(saasCreditCarrier, saasCreditCarrierVo);
        saasCreditCarrierVo.setSaasCreditCarrierId(saasCreditCarrier.getId());
        return saasCreditCarrierVo;
    }

    public static SaasCreditCarrier convertVOToEntity(SaasCreditCarrierVo saasCreditCarrierVo) {
        if (saasCreditCarrierVo == null) {
            return null;
        }
        SaasCreditCarrier saasCreditCarrier = new SaasCreditCarrier();
        BeanUtils.copyProperties(saasCreditCarrierVo, saasCreditCarrier);
        saasCreditCarrier.setId(saasCreditCarrierVo.getSaasCreditCarrierId());
        return saasCreditCarrier;
    }

}
