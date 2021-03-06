package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerToken;
import com.beitu.saas.common.consts.TimeConsts;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.TimeUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.899
 */
public class SaasBorrowerTokenVo implements ResponseData, Serializable {

    private Long saasBorrowerTokenId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 授权令牌
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireDate;

    public Long getSaasBorrowerTokenId() {
        return saasBorrowerTokenId;
    }

    public void setSaasBorrowerTokenId(Long saasBorrowerTokenId) {
        this.saasBorrowerTokenId = saasBorrowerTokenId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public static SaasBorrowerTokenVo convertEntityToVO(SaasBorrowerToken saasBorrowerToken) {
        if (saasBorrowerToken == null) {
            return null;
        }
        SaasBorrowerTokenVo saasBorrowerTokenVo = new SaasBorrowerTokenVo();
        BeanUtils.copyProperties(saasBorrowerToken, saasBorrowerTokenVo);
        saasBorrowerTokenVo.setSaasBorrowerTokenId(saasBorrowerToken.getId());
        return saasBorrowerTokenVo;
    }

    public static SaasBorrowerToken convertVOToEntity(SaasBorrowerTokenVo saasBorrowerTokenVo) {
        if (saasBorrowerTokenVo == null) {
            return null;
        }
        SaasBorrowerToken saasBorrowerToken = new SaasBorrowerToken();
        BeanUtils.copyProperties(saasBorrowerTokenVo, saasBorrowerToken);
        saasBorrowerToken.setId(saasBorrowerTokenVo.getSaasBorrowerTokenId());
        saasBorrowerToken.setToken(saasBorrowerToken.createToken());
        saasBorrowerToken.setExpireDate(saasBorrowerToken.createExpireDate());
        return saasBorrowerToken;
    }

}
