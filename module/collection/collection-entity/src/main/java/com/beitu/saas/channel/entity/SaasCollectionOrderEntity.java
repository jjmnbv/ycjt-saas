package com.beitu.saas.channel.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-22
* Time: 14:00:52.709
* TableDesc:订单表
*/
public class SaasCollectionOrderEntity extends BaseEntity{
    /**
    *订单号
    */
    private String orderNo;
    /**
    *订单状态 0-未结清 1-已结清
    */
    private Integer status;


    public  String getOrderNo(){
        return this.orderNo;
    }

    public  SaasCollectionOrderEntity setOrderNo(String orderNo){
        this.orderNo = orderNo;
        return this;
    }

    public  Integer getStatus(){
        return this.status;
    }

    public  SaasCollectionOrderEntity setStatus(Integer status){
        this.status = status;
        return this;
    }
}
