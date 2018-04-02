package com.beitu.saas.intergration.esign.param;

/**
 * @author linanjun
 * @create 2018/3/30 下午3:49
 * @description
 */
public class LoanContractSignParam {

    private String merchantCode;

    private String merchantAccountId;

    private String merchantSealData;

    private String borrowerCode;

    private String borrowerAccountId;

    private String borrowerSealData;

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

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getBorrowerAccountId() {
        return borrowerAccountId;
    }

    public void setBorrowerAccountId(String borrowerAccountId) {
        this.borrowerAccountId = borrowerAccountId;
    }

    public String getBorrowerSealData() {
        return borrowerSealData;
    }

    public void setBorrowerSealData(String borrowerSealData) {
        this.borrowerSealData = borrowerSealData;
    }

    public String getSrcPdf() {
        return srcPdf;
    }

    public void setSrcPdf(String srcPdf) {
        this.srcPdf = srcPdf;
    }

}
