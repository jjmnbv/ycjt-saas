package com.beitu.saas.intergration.esign.param;

/**
 * @author linanjun
 * @create 2018/3/30 下午3:49
 * @description
 */
public class LoanContractSignParam {

    private Long merchantId;

    private String merchantAccountId;

    private String merchantSealData;

    private Long borrowerId;

    private String borrowerAccountId;

    private String borrowerSealData;

    private String srcPdf;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
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
