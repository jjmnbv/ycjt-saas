package com.beitu.saas.rest.controller.auth.response;

import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.entity.SaasSmsConfigDictionary;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xiaochong
 * @create 2018/3/26 上午11:33
 * @description
 */
@ApiModel
public class MerchantInfoResponse {

    @ApiModelProperty("机构信息")
    private MerchantInfo merchantInfo;

    @ApiModelProperty("放款人")
    private LenderInfo lenderInfo;

    @ApiModelProperty("合同设置 21个人合同，22公司合同")
    private Integer contractType;

    @ApiModelProperty("短信配置")
    private List<SmsConfigInfo> smsConfigInfo;

    public MerchantInfoResponse(SaasMerchantVo saasMerchantVo, List<SaasSmsConfigDictionary> saasSmsConfigDictionaryList, List<String> smsConfig, Boolean isCompanyContract) {
        merchantInfo = new MerchantInfo();
        BeanUtils.copyProperties(saasMerchantVo, merchantInfo);
        lenderInfo = new LenderInfo();
        BeanUtils.copyProperties(saasMerchantVo, lenderInfo);
        this.contractType = isCompanyContract ? ContractConfigTypeEnum.COMPANY_CONTRACT.getKey() : ContractConfigTypeEnum.PERSONAL_CONTRACT.getKey();
        smsConfigInfo = new ArrayList<>();
        saasSmsConfigDictionaryList.forEach(saasSmsConfigDictionary -> {
            SmsConfigInfo smsConfigInfo = new SmsConfigInfo();
            BeanUtils.copyProperties(saasSmsConfigDictionary, smsConfigInfo);
            smsConfigInfo.setEnable(smsConfig.contains(saasSmsConfigDictionary.getBizCode()));
        });
    }

    public MerchantInfo getMerchantInfo() {
        return merchantInfo;
    }

    public void setMerchantInfo(MerchantInfo merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    public LenderInfo getLenderInfo() {
        return lenderInfo;
    }

    public void setLenderInfo(LenderInfo lenderInfo) {
        this.lenderInfo = lenderInfo;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public List<SmsConfigInfo> getSmsConfigInfo() {
        return smsConfigInfo;
    }

    public void setSmsConfigInfo(List<SmsConfigInfo> smsConfigInfo) {
        this.smsConfigInfo = smsConfigInfo;
    }

    class MerchantInfo {

        @ApiModelProperty("公司名称")
        private String companyName;

        @ApiModelProperty("公司邮箱")
        private String companyMail;

        @ApiModelProperty("公司电话")
        private String companyTel;

        @ApiModelProperty("公司地址")
        private String companyAddress;

        @ApiModelProperty("统一信用代码")
        private String creditCode;

        @ApiModelProperty("法人")
        private String jurisdicalPerson;

        @ApiModelProperty("法人身份证")
        private String jurisdicalPersonIdcard;

        @ApiModelProperty("合同章url")
        private String contractSealUrl;

        @ApiModelProperty("营业执照")
        private String businessLicenceUrl;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyMail() {
            return companyMail;
        }

        public void setCompanyMail(String companyMail) {
            this.companyMail = companyMail;
        }

        public String getCompanyTel() {
            return companyTel;
        }

        public void setCompanyTel(String companyTel) {
            this.companyTel = companyTel;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getCreditCode() {
            return creditCode;
        }

        public void setCreditCode(String creditCode) {
            this.creditCode = creditCode;
        }

        public String getJurisdicalPerson() {
            return jurisdicalPerson;
        }

        public void setJurisdicalPerson(String jurisdicalPerson) {
            this.jurisdicalPerson = jurisdicalPerson;
        }

        public String getJurisdicalPersonIdcard() {
            return jurisdicalPersonIdcard;
        }

        public void setJurisdicalPersonIdcard(String jurisdicalPersonIdcard) {
            this.jurisdicalPersonIdcard = jurisdicalPersonIdcard;
        }

        public String getContractSealUrl() {
            return contractSealUrl;
        }

        public void setContractSealUrl(String contractSealUrl) {
            this.contractSealUrl = contractSealUrl;
        }

        public String getBusinessLicenceUrl() {
            return businessLicenceUrl;
        }

        public void setBusinessLicenceUrl(String businessLicenceUrl) {
            this.businessLicenceUrl = businessLicenceUrl;
        }
    }

    class LenderInfo {

        @ApiModelProperty("放款人")
        private String lenderName;

        @ApiModelProperty("放款人身份证号码")
        private String lenderIdcard;

        @ApiModelProperty("放款人手机号码")
        private String lenderTel;

        public String getLenderName() {
            return lenderName;
        }

        public void setLenderName(String lenderName) {
            this.lenderName = lenderName;
        }

        public String getLenderIdcard() {
            return lenderIdcard;
        }

        public void setLenderIdcard(String lenderIdcard) {
            this.lenderIdcard = lenderIdcard;
        }

        public String getLenderTel() {
            return lenderTel;
        }

        public void setLenderTel(String lenderTel) {
            this.lenderTel = lenderTel;
        }
    }

    class SmsConfigInfo {

        @ApiModelProperty("配置Id")
        private String bizCode;

        @ApiModelProperty("短信类型")
        private String smsType;

        @ApiModelProperty("接受对象")
        private String smsReceiver;

        @ApiModelProperty("短信内容")
        private String smsContent;

        @ApiModelProperty("启用")
        private Boolean enable;

        public String getBizCode() {
            return bizCode;
        }

        public void setBizCode(String bizCode) {
            this.bizCode = bizCode;
        }

        public String getSmsType() {
            return smsType;
        }

        public void setSmsType(String smsType) {
            this.smsType = smsType;
        }

        public String getSmsReceiver() {
            return smsReceiver;
        }

        public void setSmsReceiver(String smsReceiver) {
            this.smsReceiver = smsReceiver;
        }

        public String getSmsContent() {
            return smsContent;
        }

        public void setSmsContent(String smsContent) {
            this.smsContent = smsContent;
        }

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }
    }
}
