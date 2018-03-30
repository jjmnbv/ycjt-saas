package com.beitu.saas.intergration.risk.param;

import com.beitu.saas.intergration.risk.enums.LoanPlatformEnum;
import org.apache.commons.lang3.StringUtils;

public class LoanPlatformCrawlingParam {
    
    /**
     * 内部用户ID
     */
    private Long userId;
    
    /**
     * 登录成功后跳转URL
     */
    private String jumpUrl;
    
    /**
     * 平台类型
     */
    private LoanPlatformEnum platformEnum;
    
    public Long getUserId() {
        return userId;
    }
    
    public LoanPlatformCrawlingParam setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    
    public String getJumpUrl() {
        return jumpUrl;
    }
    
    public LoanPlatformCrawlingParam setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
        return this;
    }
    
    public LoanPlatformEnum getPlatformEnum() {
        return platformEnum;
    }
    
    public LoanPlatformCrawlingParam setPlatformEnum(LoanPlatformEnum platformEnum) {
        this.platformEnum = platformEnum;
        return this;
    }
    
    public String validate() {
        
        if (null == this.userId) {
            return "用户ID为空";
        }
        
        if (StringUtils.isEmpty(this.jumpUrl)) {
            return "成功跳转URL为空";
        }
        
        if (null == this.platformEnum) {
            return "平台类型为空";
        }
        
        return null;
    }
}
