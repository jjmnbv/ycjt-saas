package com.beitu.saas.intergration.risk.param;

import com.beitu.saas.intergration.risk.enums.LoanPlatformEnum;
import org.apache.commons.lang3.StringUtils;

public class LoanPlatformCrawlingParam {
    
    /**
     * 任务标识，确保唯一性
     */
    private String taskId;
    
    /**
     * 登录成功后跳转URL
     */
    private String jumpUrl;
    
    /**
     * 平台类型
     */
    private LoanPlatformEnum platformEnum;
    
    public String getTaskId() {
        return taskId;
    }
    
    public LoanPlatformCrawlingParam setTaskId(String taskId) {
        this.taskId = taskId;
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
        
        if (StringUtils.isEmpty(this.taskId)) {
            return "任务标识为空";
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
