package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.650
* TableDesc:机构表
*/
public class SaasMerchant extends BaseEntity {
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


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasMerchant setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getCompanyName(){
        return this.companyName;
    }

    public  SaasMerchant setCompanyName(String companyName){
        this.companyName = companyName;
        return this;
    }

    public  String getCompanyMail(){
        return this.companyMail;
    }

    public  SaasMerchant setCompanyMail(String companyMail){
        this.companyMail = companyMail;
        return this;
    }

    public  String getCompanyTel(){
        return this.companyTel;
    }

    public  SaasMerchant setCompanyTel(String companyTel){
        this.companyTel = companyTel;
        return this;
    }

    public  String getCompanyAddress(){
        return this.companyAddress;
    }

    public  SaasMerchant setCompanyAddress(String companyAddress){
        this.companyAddress = companyAddress;
        return this;
    }

    public  String getCreditCode(){
        return this.creditCode;
    }

    public  SaasMerchant setCreditCode(String creditCode){
        this.creditCode = creditCode;
        return this;
    }

    public  String getJurisdicalPerson(){
        return this.jurisdicalPerson;
    }

    public  SaasMerchant setJurisdicalPerson(String jurisdicalPerson){
        this.jurisdicalPerson = jurisdicalPerson;
        return this;
    }

    public  String getJurisdicalPersonIdcard(){
        return this.jurisdicalPersonIdcard;
    }

    public  SaasMerchant setJurisdicalPersonIdcard(String jurisdicalPersonIdcard){
        this.jurisdicalPersonIdcard = jurisdicalPersonIdcard;
        return this;
    }

    public  String getContractSealUrl(){
        return this.contractSealUrl;
    }

    public  SaasMerchant setContractSealUrl(String contractSealUrl){
        this.contractSealUrl = contractSealUrl;
        return this;
    }

    public  String getBusinessLicenceUrl(){
        return this.businessLicenceUrl;
    }

    public  SaasMerchant setBusinessLicenceUrl(String businessLicenceUrl){
        this.businessLicenceUrl = businessLicenceUrl;
        return this;
    }

    public  String getLenderName(){
        return this.lenderName;
    }

    public  SaasMerchant setLenderName(String lenderName){
        this.lenderName = lenderName;
        return this;
    }

    public  String getLenderIdcard(){
        return this.lenderIdcard;
    }

    public  SaasMerchant setLenderIdcard(String lenderIdcard){
        this.lenderIdcard = lenderIdcard;
        return this;
    }

    public  String getLenderTel(){
        return this.lenderTel;
    }

    public  SaasMerchant setLenderTel(String lenderTel){
        this.lenderTel = lenderTel;
        return this;
    }

    public  String getPassword(){
        return this.password;
    }

    public  SaasMerchant setPassword(String password){
        this.password = password;
        return this;
    }
}
