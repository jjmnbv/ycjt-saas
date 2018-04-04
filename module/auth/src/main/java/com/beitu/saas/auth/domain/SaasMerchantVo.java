package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.651
*/
public class SaasMerchantVo implements ResponseData,Serializable{

    private Long saasMerchantId;

    /**
    *code
    */
    private String merchantCode;
    /**
    *公司名称
    */
    private String companyName;
    /**
    *
    */
    private String companyMail;
    /**
    *
    */
    private String companyTel;
    /**
    *
    */
    private String companyAddress;
    /**
    *统一信用代码
    */
    private String creditCode;
    /**
    *法人
    */
    private String jurisdicalPerson;
    /**
    *法人身份证
    */
    private String jurisdicalPersonIdcard;
    /**
    *合同章url
    */
    private String contractSealUrl;
    /**
    *营业执照
    */
    private String businessLicenceUrl;
    /**
    *放款人
    */
    private String lenderName;
    /**
    *放款人身份证号码
    */
    private String lenderIdcard;
    /**
    *放款人手机号码
    */
    private String lenderTel;
    /**
    *支付密码
    */
    private String password;
    /**
     *允许新建子账户个数
     */
    private Integer allowAccountNum;

    public Integer getAllowAccountNum() {
        return allowAccountNum;
    }

    public void setAllowAccountNum(Integer allowAccountNum) {
        this.allowAccountNum = allowAccountNum;
    }

    public Long getSaasMerchantId() {
        return saasMerchantId;
    }

    public void setSaasMerchantId(Long saasMerchantId) {
        this.saasMerchantId = saasMerchantId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  String getCompanyName(){
        return this.companyName;
    }

    public  void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public  String getCompanyMail(){
        return this.companyMail;
    }

    public  void setCompanyMail(String companyMail){
        this.companyMail = companyMail;
    }

    public  String getCompanyTel(){
        return this.companyTel;
    }

    public  void setCompanyTel(String companyTel){
        this.companyTel = companyTel;
    }

    public  String getCompanyAddress(){
        return this.companyAddress;
    }

    public  void setCompanyAddress(String companyAddress){
        this.companyAddress = companyAddress;
    }

    public  String getCreditCode(){
        return this.creditCode;
    }

    public  void setCreditCode(String creditCode){
        this.creditCode = creditCode;
    }

    public  String getJurisdicalPerson(){
        return this.jurisdicalPerson;
    }

    public  void setJurisdicalPerson(String jurisdicalPerson){
        this.jurisdicalPerson = jurisdicalPerson;
    }

    public  String getJurisdicalPersonIdcard(){
        return this.jurisdicalPersonIdcard;
    }

    public  void setJurisdicalPersonIdcard(String jurisdicalPersonIdcard){
        this.jurisdicalPersonIdcard = jurisdicalPersonIdcard;
    }

    public  String getContractSealUrl(){
        return this.contractSealUrl;
    }

    public  void setContractSealUrl(String contractSealUrl){
        this.contractSealUrl = contractSealUrl;
    }

    public  String getBusinessLicenceUrl(){
        return this.businessLicenceUrl;
    }

    public  void setBusinessLicenceUrl(String businessLicenceUrl){
        this.businessLicenceUrl = businessLicenceUrl;
    }

    public  String getLenderName(){
        return this.lenderName;
    }

    public  void setLenderName(String lenderName){
        this.lenderName = lenderName;
    }

    public  String getLenderIdcard(){
        return this.lenderIdcard;
    }

    public  void setLenderIdcard(String lenderIdcard){
        this.lenderIdcard = lenderIdcard;
    }

    public  String getLenderTel(){
        return this.lenderTel;
    }

    public  void setLenderTel(String lenderTel){
        this.lenderTel = lenderTel;
    }

    public  String getPassword(){
        return this.password;
    }

    public  void setPassword(String password){
        this.password = password;
    }
}
