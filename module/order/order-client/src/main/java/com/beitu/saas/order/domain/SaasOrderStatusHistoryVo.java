package com.beitu.saas.order.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: jungle
* Date: 2018-03-25
* Time: 21:55:45.880
*/
public class SaasOrderStatusHistoryVo implements ResponseData,Serializable{

    private Long saasOrderStatusHistoryId;

    /**
    *订单ID
    */
    private String orderId;
    /**
    *订单号
    */
    private String orderNumb;
    /**
    *当前订单状态
    */
    private Integer currentOrderStatus;
    /**
    *更新后订单状态
    */
    private Integer updateOrderStatus;
    /**
    *操作人CODE
    */
    private String operatorCode;
    /**
    *备注
    */
    private String remark;

    public Long getSaasOrderStatusHistoryId() {
        return saasOrderStatusHistoryId;
    }

    public void setSaasOrderStatusHistoryId(Long saasOrderStatusHistoryId) {
        this.saasOrderStatusHistoryId = saasOrderStatusHistoryId;
    }



    public  String getOrderId(){
        return this.orderId;
    }

    public  void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public  String getOrderNumb(){
        return this.orderNumb;
    }

    public  void setOrderNumb(String orderNumb){
        this.orderNumb = orderNumb;
    }

    public  Integer getCurrentOrderStatus(){
        return this.currentOrderStatus;
    }

    public  void setCurrentOrderStatus(Integer currentOrderStatus){
        this.currentOrderStatus = currentOrderStatus;
    }

    public  Integer getUpdateOrderStatus(){
        return this.updateOrderStatus;
    }

    public  void setUpdateOrderStatus(Integer updateOrderStatus){
        this.updateOrderStatus = updateOrderStatus;
    }

    public  String getOperatorCode(){
        return this.operatorCode;
    }

    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }

    public  String getRemark(){
        return this.remark;
    }

    public  void setRemark(String remark){
        this.remark = remark;
    }
}
