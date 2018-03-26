package com.beitu.saas.collection.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-26
* Time: 15:11:49.041
* TableDesc:订单催收表
*/
public class SaasCollectionCommentEntity extends BaseEntity{
    /**
    *订单号
    */
    private String orderNo;
    /**
    *姓名
    */
    private String name;
    /**
    *手机号
    */
    private String mobile;
    /**
    *内容
    */
    private String content;
    /**
    *跟进人code
    */
    private String followCode;
    /**
    *跟进人名字
    */
    private String followUp;


    public  String getOrderNo(){
        return this.orderNo;
    }

    public  SaasCollectionCommentEntity setOrderNo(String orderNo){
        this.orderNo = orderNo;
        return this;
    }

    public  String getName(){
        return this.name;
    }

    public  SaasCollectionCommentEntity setName(String name){
        this.name = name;
        return this;
    }

    public  String getMobile(){
        return this.mobile;
    }

    public  SaasCollectionCommentEntity setMobile(String mobile){
        this.mobile = mobile;
        return this;
    }

    public  String getContent(){
        return this.content;
    }

    public  SaasCollectionCommentEntity setContent(String content){
        this.content = content;
        return this;
    }

    public  String getFollowCode(){
        return this.followCode;
    }

    public  SaasCollectionCommentEntity setFollowCode(String followCode){
        this.followCode = followCode;
        return this;
    }

    public  String getFollowUp(){
        return this.followUp;
    }

    public  SaasCollectionCommentEntity setFollowUp(String followUp){
        this.followUp = followUp;
        return this;
    }
}
