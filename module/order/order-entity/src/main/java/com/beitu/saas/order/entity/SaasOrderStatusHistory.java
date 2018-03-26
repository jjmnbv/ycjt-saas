package com.beitu.saas.order.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-26
 * Time: 16:49:01.261
 * TableDesc:SAAS订单状态流水表
 */
public class SaasOrderStatusHistory extends BaseEntity {
    /**
     * 订单ID
     */
    private String orderId;
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


    public String getOrderId() {
        return this.orderId;
    }

    public SaasOrderStatusHistory setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public SaasOrderStatusHistory setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
        return this;
    }

    public Integer getCurrentOrderStatus() {
        return this.currentOrderStatus;
    }

    public SaasOrderStatusHistory setCurrentOrderStatus(Integer currentOrderStatus) {
        this.currentOrderStatus = currentOrderStatus;
        return this;
    }

    public Integer getUpdateOrderStatus() {
        return this.updateOrderStatus;
    }

    public SaasOrderStatusHistory setUpdateOrderStatus(Integer updateOrderStatus) {
        this.updateOrderStatus = updateOrderStatus;
        return this;
    }

    public String getOperatorCode() {
        return this.operatorCode;
    }

    public SaasOrderStatusHistory setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
        return this;
    }

    public String getRemark() {
        return this.remark;
    }

    public SaasOrderStatusHistory setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
