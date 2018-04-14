package com.beitu.saas.order.domain;

import com.beitu.saas.order.entity.SaasOrderLendRemark;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-04-12
 * Time: 20:39:32.664
 */
public class SaasOrderLendRemarkVo implements ResponseData, Serializable {

    private Long saasOrderLendRemarkId;

    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 放款途径
     */
    private String lendWay;
    /**
     * 放款人
     */
    private String lendPersonCode;

    public Long getSaasOrderLendRemarkId() {
        return saasOrderLendRemarkId;
    }

    public void setSaasOrderLendRemarkId(Long saasOrderLendRemarkId) {
        this.saasOrderLendRemarkId = saasOrderLendRemarkId;
    }


    public String getOrderNumb() {
        return this.orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getLendWay() {
        return this.lendWay;
    }

    public void setLendWay(String lendWay) {
        this.lendWay = lendWay;
    }

    public String getLendPersonCode() {
        return this.lendPersonCode;
    }

    public void setLendPersonCode(String lendPersonCode) {
        this.lendPersonCode = lendPersonCode;
    }

    public static SaasOrderLendRemarkVo convertEntityToVO(SaasOrderLendRemark saasOrderLendRemark) {
        if (saasOrderLendRemark == null) {
            return null;
        }
        SaasOrderLendRemarkVo saasOrderLendRemarkVo = new SaasOrderLendRemarkVo();
        BeanUtils.copyProperties(saasOrderLendRemark, saasOrderLendRemarkVo);
        saasOrderLendRemarkVo.setSaasOrderLendRemarkId(saasOrderLendRemark.getId());
        return saasOrderLendRemarkVo;
    }

    public static SaasOrderLendRemark convertVOToEntity(SaasOrderLendRemarkVo saasOrderLendRemarkVo) {
        if (saasOrderLendRemarkVo == null) {
            return null;
        }
        SaasOrderLendRemark saasOrderLendRemark = new SaasOrderLendRemark();
        BeanUtils.copyProperties(saasOrderLendRemarkVo, saasOrderLendRemark);
        saasOrderLendRemark.setId(saasOrderLendRemarkVo.getSaasOrderLendRemarkId());
        return saasOrderLendRemark;
    }

}