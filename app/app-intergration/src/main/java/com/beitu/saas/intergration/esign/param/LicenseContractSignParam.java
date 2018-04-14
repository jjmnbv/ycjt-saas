package com.beitu.saas.intergration.esign.param;

/**
 * @author linanjun
 * @create 2018/3/30 下午3:49
 * @description
 */
public class LicenseContractSignParam {

    private String userCode;

    private String userAccountId;

    private String userSealData;

    private byte[] srcPdfContent;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserSealData() {
        return userSealData;
    }

    public void setUserSealData(String userSealData) {
        this.userSealData = userSealData;
    }

    public byte[] getSrcPdfContent() {
        return srcPdfContent;
    }

    public void setSrcPdfContent(byte[] srcPdfContent) {
        this.srcPdfContent = srcPdfContent;
    }
}
