package com.beitu.saas.intergration.risk.param;

import org.apache.commons.lang3.StringUtils;

public class LoanPlatformValidatePrefixParam {
    
    private String timestamp;
    
    private String userCode;
    
    private String website;
    
    private String prefix;
    
    public LoanPlatformValidatePrefixParam(String timestamp, String userCode, String website, String prefix) {
        this.timestamp = timestamp;
        this.userCode = userCode;
        this.website = website;
        this.prefix = prefix;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getUserCode() {
        return userCode;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public Boolean validate() {
        
        if (StringUtils.isEmpty(this.timestamp)) {
            return Boolean.FALSE;
        }
        
        if (StringUtils.isEmpty(this.userCode)) {
            return Boolean.FALSE;
        }
        
        if (StringUtils.isEmpty(this.website)) {
            return Boolean.FALSE;
        }
        
        if (StringUtils.isEmpty(this.prefix)) {
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }
}
