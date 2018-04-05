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

    private byte[] srcPdfContent;

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

    public byte[] getSrcPdfContent() {
        return srcPdfContent;
    }

    public void setSrcPdfContent(byte[] srcPdfContent) {
        this.srcPdfContent = srcPdfContent;
    }

}
