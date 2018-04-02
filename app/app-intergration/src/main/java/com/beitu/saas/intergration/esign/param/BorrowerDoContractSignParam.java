package com.beitu.saas.intergration.esign.param;

/**
 * @author linanjun
 * @create 2018/3/30 下午3:49
 * @description
 */
public class BorrowerDoContractSignParam {

    private String borrowerCode;

    private String borrowerAccountId;

    private String borrowerSealData;

    private byte[] srcContent;

    private String srcPdf;

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
