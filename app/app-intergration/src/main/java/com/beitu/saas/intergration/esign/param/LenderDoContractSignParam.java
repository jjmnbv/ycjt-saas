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

    private byte[] srcPdfContent;

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

    public byte[] getSrcPdfContent() {
        return srcPdfContent;
    }

    public void setSrcPdfContent(byte[] srcPdfContent) {
        this.srcPdfContent = srcPdfContent;
    }

}
