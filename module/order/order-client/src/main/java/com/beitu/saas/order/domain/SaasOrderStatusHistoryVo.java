package com.beitu.saas.order.domain;

import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-26
 * Time: 17:00:42.371
 */
public class SaasOrderStatusHistoryVo implements ResponseData, Serializable {

    private Long saasOrderStatusHistoryId;

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 当前订单状态
     */
    private Integer currentOrderStatus;
    /**
     * 更新后订单状态
     */
    private Integer updateOrderStatus;
    /**
     * 操作人CODE
     */
    private String operatorCode;
    /**
     * 备注
     */
    private String remark;

    private Date gmtCreate;

    public Long getSaasOrderStatusHistoryId() {
        return saasOrderStatusHistoryId;
    }

    public void setSaasOrderStatusHistoryId(Long saasOrderStatusHistoryId) {
        this.saasOrderStatusHistoryId = saasOrderStatusHistoryId;
    }


    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public Integer getCurrentOrderStatus() {
        return this.currentOrderStatus;
    }

    public void setCurrentOrderStatus(Integer currentOrderStatus) {
        this.currentOrderStatus = currentOrderStatus;
    }

    public Integer getUpdateOrderStatus() {
        return this.updateOrderStatus;
    }

    public void setUpdateOrderStatus(Integer updateOrderStatus) {
        this.updateOrderStatus = updateOrderStatus;
    }

    public String getOperatorCode() {
        return this.operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public static SaasOrderStatusHistoryVo convertEntityToVO(SaasOrderStatusHistory saasOrderStatusHistory) {
        if (saasOrderStatusHistory == null) {
            return null;
        }
        SaasOrderStatusHistoryVo saasOrderStatusHistoryVo = new SaasOrderStatusHistoryVo();
        BeanUtils.copyProperties(saasOrderStatusHistory, saasOrderStatusHistoryVo);
        saasOrderStatusHistoryVo.setSaasOrderStatusHistoryId(saasOrderStatusHistory.getId());
        return saasOrderStatusHistoryVo;
    }

    public static SaasOrderStatusHistory convertVOToEntity(SaasOrderStatusHistoryVo saasOrderStatusHistoryVo) {
        if (saasOrderStatusHistoryVo == null) {
            return null;
        }
        SaasOrderStatusHistory saasOrderStatusHistory = new SaasOrderStatusHistory();
        BeanUtils.copyProperties(saasOrderStatusHistoryVo, saasOrderStatusHistory);
        saasOrderStatusHistory.setId(saasOrderStatusHistoryVo.getSaasOrderStatusHistoryId());
        return saasOrderStatusHistory;
    }

}
