package com.beitu.saas.intergration.esign.param;

/**
 * @author linanjun
 * @create 2018/3/30 下午3:49
 * @description
 */
public class LenderDoContractSignParam {

    private String merchantCode;

    private String merchantAccountId;

    private String merchantSealData;

    private byte[] srcContent;

    private String srcPdf;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(String merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public String getMerchantSealData() {
        return merchantSealData;
    }

    public void setMerchantSealData(String merchantSealData) {
        this.merchantSealData = merchantSealData;
    }

    public byte[] getSrcContent() {
        return srcContent;
    }

    public void setSrcContent(byte[] srcContent) {
        this.srcContent = srcContent;
    }

    public String getSrcPdf() {
        return srcPdf;
    }

    public void setSrcPdf(String srcPdf) {
        this.srcPdf = srcPdf;
    }
}
