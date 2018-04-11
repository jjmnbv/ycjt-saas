package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-10
 * Time: 16:49:47.280
 * TableDesc:SAAS借款人身份证信息表
 */
public class SaasBorrowerIdentityInfo extends BaseEntity {
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 身份证正面面图片URL
     */
    private String frontUrl;
    /**
     * 身份证反面图片URL
     */
    private String backUrl;
    /**
     * 手持身份证图片URL
     */
    private String holdUrl;


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasBorrowerIdentityInfo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public SaasBorrowerIdentityInfo setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
        return this;
    }

    public String getFrontUrl() {
        return this.frontUrl;
    }

    public SaasBorrowerIdentityInfo setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
        return this;
    }

    public String getBackUrl() {
        return this.backUrl;
    }

    public SaasBorrowerIdentityInfo setBackUrl(String backUrl) {
        this.backUrl = backUrl;
        return this;
    }

    public String getHoldUrl() {
        return this.holdUrl;
    }

    public SaasBorrowerIdentityInfo setHoldUrl(String holdUrl) {
        this.holdUrl = holdUrl;
        return this;
    }
}
