package com.beitu.saas.intergration.risk.pojo;

public class LoanPlatformQueryDetailPojo {
    
    private String id;
    
    private String token;
    
    private String sourceName;
    
    private String sourceDesc;
    
    private String updateTime;
    
    private String dataType;
    
    private LoanPlatformDataPojo data;
    
    public String getId() {
        return id;
    }
    
    public String getToken() {
        return token;
    }
    
    public String getSourceName() {
        return sourceName;
    }
    
    public String getSourceDesc() {
        return sourceDesc;
    }
    
    public String getUpdateTime() {
        return updateTime;
    }
    
    public String getDataType() {
        return dataType;
    }
    
    public LoanPlatformDataPojo getData() {
        return data;
    }
}
