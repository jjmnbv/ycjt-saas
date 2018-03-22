package com.beitu.saas.collection.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.358
* TableDesc:订单表
*/
public class SaasCollectionOrderEntity extends BaseEntity{
    /**
    *订单号
    */
    private String orderNo;


    public  String getOrderNo(){
        return this.orderNo;
    }

    public  SaasCollectionOrderEntity setOrderNo(String orderNo){
        this.orderNo = orderNo;
        return this;
    }
}
