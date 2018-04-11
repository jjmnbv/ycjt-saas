package com.beitu.saas.app.application.openapi.vo;

public class SaasBorrowerRelatedDataVo {
    
    private String borrowerCode;
    
    private String merchantCode;
    
    private String channelCode;
    
    public SaasBorrowerRelatedDataVo(String borrowerCode, String merchantCode, String channelCode) {
        this.borrowerCode = borrowerCode;
        this.merchantCode = merchantCode;
        this.channelCode = channelCode;
    }
    
    public String getBorrowerCode() {
        return borrowerCode;
    }
    
    public String getMerchantCode() {
        return merchantCode;
    }
    
    public String getChannelCode() {
        return channelCode;
    }
}
