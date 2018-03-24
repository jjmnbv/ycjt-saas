package com.beitu.saas.finance.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:10.951
* TableDesc:账户点券流水表
*/
public class SaasCreditHistoryEntity extends BaseEntity{
    /**
    *机构码
    */
    private String merchantCode;
    /**
    *余额操作类型，0：支出，1：收入
    */
    private Integer opType;
    /**
    *点券
    */
    private Long credit;
    /**
    *操作后点券
    */
    private Long currentCredit;
    /**
    *备注信息
    */
    private String comment;
    /**
    *操作人
    */
    private String opName;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasCreditHistoryEntity setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  Integer getOpType(){
        return this.opType;
    }

    public  SaasCreditHistoryEntity setOpType(Integer opType){
        this.opType = opType;
        return this;
    }

    public  Long getCredit(){
        return this.credit;
    }

    public  SaasCreditHistoryEntity setCredit(Long credit){
        this.credit = credit;
        return this;
    }

    public  Long getCurrentCredit(){
        return this.currentCredit;
    }

    public  SaasCreditHistoryEntity setCurrentCredit(Long currentCredit){
        this.currentCredit = currentCredit;
        return this;
    }

    public  String getComment(){
        return this.comment;
    }

    public  SaasCreditHistoryEntity setComment(String comment){
        this.comment = comment;
        return this;
    }

    public  String getOpName(){
        return this.opName;
    }

    public  SaasCreditHistoryEntity setOpName(String opName){
        this.opName = opName;
        return this;
    }
}
