package com.beitu.saas.borrower.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-03-22
* Time: 20:25:59.867
* TableDesc:SAAS借款人紧急联系人信息表
*/
public class SaasBorrowerEmergentContact extends BaseEntity{
    /**
    *借款人CODE
    */
    private String borrowerCode;
    /**
    *订单号
    */
    private String orderNumb;
    /**
    *直系亲属联系人类型
    */
    private String familyType;
    /**
    *直系亲属联系人输入姓名
    */
    private String familyName;
    /**
    *直系亲属联系人手机号
    */
    private String familyMobile;
    /**
    *同事朋友联系人类型
    */
    private String friendType;
    /**
    *同事朋友联系人输入姓名
    */
    private String friendName;
    /**
    *同事朋友联系人手机号
    */
    private String friendMobile;
    /**
    *是否成功
    */
    private Boolean success;


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasBorrowerEmergentContact setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getOrderNumb(){
        return this.orderNumb;
    }

    public  SaasBorrowerEmergentContact setOrderNumb(String orderNumb){
        this.orderNumb = orderNumb;
        return this;
    }

    public  String getFamilyType(){
        return this.familyType;
    }

    public  SaasBorrowerEmergentContact setFamilyType(String familyType){
        this.familyType = familyType;
        return this;
    }

    public  String getFamilyName(){
        return this.familyName;
    }

    public  SaasBorrowerEmergentContact setFamilyName(String familyName){
        this.familyName = familyName;
        return this;
    }

    public  String getFamilyMobile(){
        return this.familyMobile;
    }

    public  SaasBorrowerEmergentContact setFamilyMobile(String familyMobile){
        this.familyMobile = familyMobile;
        return this;
    }

    public  String getFriendType(){
        return this.friendType;
    }

    public  SaasBorrowerEmergentContact setFriendType(String friendType){
        this.friendType = friendType;
        return this;
    }

    public  String getFriendName(){
        return this.friendName;
    }

    public  SaasBorrowerEmergentContact setFriendName(String friendName){
        this.friendName = friendName;
        return this;
    }

    public  String getFriendMobile(){
        return this.friendMobile;
    }

    public  SaasBorrowerEmergentContact setFriendMobile(String friendMobile){
        this.friendMobile = friendMobile;
        return this;
    }

    public  Boolean getSuccess(){
        return this.success;
    }

    public  SaasBorrowerEmergentContact setSuccess(Boolean success){
        this.success = success;
        return this;
    }
}
