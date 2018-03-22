package com.beitu.saas.borrower.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-03-22
* Time: 20:25:59.857
* TableDesc:SAAS借款人运营商报告扩充资料表
*/
public class SaasBorrowerCarrierExt extends BaseEntity{
    /**
    *借款人CODE
    */
    private String borrowerCode;
    /**
    *活跃地区
    */
    private String activeRegion;
    /**
    *入网地区
    */
    private String registerRegion;
    /**
    *联系人最多地区
    */
    private String mostContactRegion;
    /**
    *互通联系人数量
    */
    private Integer interactionCount;
    /**
    *总通话时长
    */
    private Integer totalDuration;
    /**
    *夜间通话时长
    */
    private Integer nightDuration;


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasBorrowerCarrierExt setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getActiveRegion(){
        return this.activeRegion;
    }

    public  SaasBorrowerCarrierExt setActiveRegion(String activeRegion){
        this.activeRegion = activeRegion;
        return this;
    }

    public  String getRegisterRegion(){
        return this.registerRegion;
    }

    public  SaasBorrowerCarrierExt setRegisterRegion(String registerRegion){
        this.registerRegion = registerRegion;
        return this;
    }

    public  String getMostContactRegion(){
        return this.mostContactRegion;
    }

    public  SaasBorrowerCarrierExt setMostContactRegion(String mostContactRegion){
        this.mostContactRegion = mostContactRegion;
        return this;
    }

    public  Integer getInteractionCount(){
        return this.interactionCount;
    }

    public  SaasBorrowerCarrierExt setInteractionCount(Integer interactionCount){
        this.interactionCount = interactionCount;
        return this;
    }

    public  Integer getTotalDuration(){
        return this.totalDuration;
    }

    public  SaasBorrowerCarrierExt setTotalDuration(Integer totalDuration){
        this.totalDuration = totalDuration;
        return this;
    }

    public  Integer getNightDuration(){
        return this.nightDuration;
    }

    public  SaasBorrowerCarrierExt setNightDuration(Integer nightDuration){
        this.nightDuration = nightDuration;
        return this;
    }
}
