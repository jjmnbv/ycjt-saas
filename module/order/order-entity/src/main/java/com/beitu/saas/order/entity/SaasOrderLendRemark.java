package com.beitu.saas.order.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-18
 * Time: 14:26:16.862
 * TableDesc:SAAS订单放款备注表
 */
public class SaasOrderLendRemark extends BaseEntity {
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 放款途径
     */
    private String lendWay;
    /**
     * 下款凭证
     */
    private String lendCertificate;
    /**
     * 放款人
     */
    private String lendPersonCode;


    public String getOrderNumb() {
        return this.orderNumb;
    }

    public SaasOrderLendRemark setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
        return this;
    }

    public String getLendWay() {
        return this.lendWay;
    }

    public SaasOrderLendRemark setLendWay(String lendWay) {
        this.lendWay = lendWay;
        return this;
    }

    public String getLendCertificate() {
        return this.lendCertificate;
    }

    public SaasOrderLendRemark setLendCertificate(String lendCertificate) {
        this.lendCertificate = lendCertificate;
        return this;
    }

    public String getLendPersonCode() {
        return this.lendPersonCode;
    }

    public SaasOrderLendRemark setLendPersonCode(String lendPersonCode) {
        this.lendPersonCode = lendPersonCode;
        return this;
    }
}
