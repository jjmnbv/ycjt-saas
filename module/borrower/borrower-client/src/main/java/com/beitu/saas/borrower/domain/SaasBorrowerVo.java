package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrower;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.fqgj.common.api.ResponseData;
import com.fqgj.log.util.UUID;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.840
 */
public class SaasBorrowerVo implements ResponseData, Serializable {

    private Long saasBorrowerId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 渠道CODE
     */
    private String channelCode;
    /**
     * 手机号
     */
    private String mobile;

    public Long getSaasBorrowerId() {
        return saasBorrowerId;
    }

    public void setSaasBorrowerId(Long saasBorrowerId) {
        this.saasBorrowerId = saasBorrowerId;
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

    public String getChannelCode() {
        return this.channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public static SaasBorrowerVo convertEntityToVO(SaasBorrower saasBorrower) {
        if (saasBorrower == null) {
            return null;
        }
        SaasBorrowerVo saasBorrowerVo = new SaasBorrowerVo();
        BeanUtils.copyProperties(saasBorrower, saasBorrowerVo);
        saasBorrowerVo.setSaasBorrowerId(saasBorrower.getId());
        return saasBorrowerVo;
    }

    public static SaasBorrower convertVOToEntity(SaasBorrowerVo saasBorrowerVo) {
        if (saasBorrowerVo == null) {
            return null;
        }
        SaasBorrower saasBorrower = new SaasBorrower();
        BeanUtils.copyProperties(saasBorrowerVo, saasBorrower);
        saasBorrower.setBorrowerCode(OrderNoUtil.makeOrderNum());
        saasBorrower.setId(saasBorrowerVo.getSaasBorrowerId());
        return saasBorrower;
    }

}
