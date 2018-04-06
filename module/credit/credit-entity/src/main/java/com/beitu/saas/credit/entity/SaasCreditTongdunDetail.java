package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.678
* TableDesc:同盾信用记录详情表
*/
public class SaasCreditTongdunDetail extends BaseEntity{
    /**
    *同盾信用记录查询表ID
    */
    private Long recordId;
    /**
    * 同盾流水编号
    */
    private String reportId;
    /**
    *同盾报告
    */
    private String tongdunReport;
    /**
    *同盾分
    */
    private String finalScore;
    /**
    *同盾结论
    */
    private String finalDecision;
    /**
    *报告条目
    */
    private String itemName;
    /**
    *报告时间
    */
    private Date reportTime;


    public  Long getRecordId(){
        return this.recordId;
    }

    public  SaasCreditTongdunDetail setRecordId(Long recordId){
        this.recordId = recordId;
        return this;
    }

    public  String getReportId(){
        return this.reportId;
    }

    public  SaasCreditTongdunDetail setReportId(String reportId){
        this.reportId = reportId;
        return this;
    }

    public  String getTongdunReport(){
        return this.tongdunReport;
    }

    public  SaasCreditTongdunDetail setTongdunReport(String tongdunReport){
        this.tongdunReport = tongdunReport;
        return this;
    }

    public  String getFinalScore(){
        return this.finalScore;
    }

    public  SaasCreditTongdunDetail setFinalScore(String finalScore){
        this.finalScore = finalScore;
        return this;
    }

    public  String getFinalDecision(){
        return this.finalDecision;
    }

    public  SaasCreditTongdunDetail setFinalDecision(String finalDecision){
        this.finalDecision = finalDecision;
        return this;
    }

    public  String getItemName(){
        return this.itemName;
    }

    public  SaasCreditTongdunDetail setItemName(String itemName){
        this.itemName = itemName;
        return this;
    }

    public  Date getReportTime(){
        return this.reportTime;
    }

    public  SaasCreditTongdunDetail setReportTime(Date reportTime){
        this.reportTime = reportTime;
        return this;
    }
}
