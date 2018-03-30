package com.beitu.saas.intergration.risk.param;

import org.apache.commons.lang3.StringUtils;

public class LoanPlatformQueryParam {
    
    /**
     * 任务凭证
     */
    private String token;
    
    public String getToken() {
        return token;
    }
    
    public LoanPlatformQueryParam setToken(String token) {
        this.token = token;
        return this;
    }
    
    public String validate() {
        
        if (StringUtils.isEmpty(this.token)) {
            return "任务凭证为空";
        }
        
        return null;
    }
}
