package com.beitu.saas.rest.controller.auth.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/26 下午8:49
 * @description
 */
@ApiModel
public class AddMerchantRequest extends ParamsObject {

    @ApiModelProperty("机构信息")
    private MerchantInfo merchantInfo;

    @ApiModelProperty("放款人")
    private LenderInfo lenderInfo;

    @ApiModelProperty("管理员信息")
    private AdminInfo adminInfo;

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

    public AdminInfo getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(AdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }

    @Override
    public void validate() {

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

        public MerchantInfo() {
        }

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

        public LenderInfo() {
        }

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

    public class AdminInfo{

        @ApiModelProperty("管理员手机")
        private String accountPhone;
        @ApiModelProperty("管理员昵称")
        private String accountName;
        @ApiModelProperty("管理员密码")
        private String password;

        public String getAccountPhone() {
            return accountPhone;
        }

        public void setAccountPhone(String accountPhone) {
            this.accountPhone = accountPhone;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
