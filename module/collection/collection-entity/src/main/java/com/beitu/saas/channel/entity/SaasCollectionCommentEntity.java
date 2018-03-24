package com.beitu.saas.channel.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.350
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
    *跟进人ID
    */
    private Integer followId;
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

    public  Integer getFollowId(){
        return this.followId;
    }

    public  SaasCollectionCommentEntity setFollowId(Integer followId){
        this.followId = followId;
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
