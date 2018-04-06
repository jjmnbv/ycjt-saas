package com.beitu.saas.credit.domain;
import com.fqgj.common.api.ResponseData;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.641
*/
public class SaasCreditCarrierBaseVo implements ResponseData,Serializable{

    private Long saasCreditCarrierBaseId;

    /**
    *运营商报告查询表ID
    */
    private Long recordId;
    /**
    *运营商名称
    */
    private String carrierType;
    /**
    *手机号
    */
    private String mobile;
    /**
    *姓名
    */
    private String name;
    /**
    *身份证号码
    */
    private String identityNo;
    /**
    *余额
    */
    private BigDecimal balance;
    /**
    *归属省份
    */
    private String province;
    /**
    *套餐名称
    */
    private String planName;
    /**
    *入网时间
    */
    private Date registerTime;

    public Long getSaasCreditCarrierBaseId() {
        return saasCreditCarrierBaseId;
    }

    public void setSaasCreditCarrierBaseId(Long saasCreditCarrierBaseId) {
        this.saasCreditCarrierBaseId = saasCreditCarrierBaseId;
    }



    public  Long getRecordId(){
        return this.recordId;
    }

    public  void setRecordId(Long recordId){
        this.recordId = recordId;
    }

    public  String getCarrierType(){
        return this.carrierType;
    }

    public  void setCarrierType(String carrierType){
        this.carrierType = carrierType;
    }

    public  String getMobile(){
        return this.mobile;
    }

    public  void setMobile(String mobile){
        this.mobile = mobile;
    }

    public  String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public  String getIdentityNo(){
        return this.identityNo;
    }

    public  void setIdentityNo(String identityNo){
        this.identityNo = identityNo;
    }

    public  BigDecimal getBalance(){
        return this.balance;
    }

    public  void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    public  String getProvince(){
        return this.province;
    }

    public  void setProvince(String province){
        this.province = province;
    }

    public  String getPlanName(){
        return this.planName;
    }

    public  void setPlanName(String planName){
        this.planName = planName;
    }

    public  Date getRegisterTime(){
        return this.registerTime;
    }

    public  void setRegisterTime(Date registerTime){
        this.registerTime = registerTime;
    }
}
