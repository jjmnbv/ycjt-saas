package com.beitu.saas.intergration.risk.param;

import com.beitu.saas.intergration.risk.enums.LoanPlatformEnum;
import org.apache.commons.lang3.StringUtils;

public class LoanPlatformTaskIdPrefixParam {
    
    private String userCode;
    
    private LoanPlatformEnum platformEnum;
    
    public LoanPlatformTaskIdPrefixParam(String userCode, LoanPlatformEnum platformEnum) {
        this.userCode = userCode;
        this.platformEnum = platformEnum;
    }
    
    public String getUserCode() {
        return userCode;
    }
    
    public LoanPlatformEnum getPlatformEnum() {
        return platformEnum;
    }
    
    public Boolean validate() {
        
        if (StringUtils.isEmpty(this.userCode)) {
            return Boolean.FALSE;
        }
        
        if (this.platformEnum == null) {
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }
}
