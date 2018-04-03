package com.beitu.saas.intergration.risk.dto;


import java.io.Serializable;

public class LoanPlatformTaskIdPrefixDto implements Serializable {
    
    private static final long serialVersionUID = -946240614230316042L;
    
    private String timestamp;
    
    private String prefix;
    
    public LoanPlatformTaskIdPrefixDto(String timestamp, String prefix) {
        this.timestamp = timestamp;
        this.prefix = prefix;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getPrefix() {
        return prefix;
    }
}
