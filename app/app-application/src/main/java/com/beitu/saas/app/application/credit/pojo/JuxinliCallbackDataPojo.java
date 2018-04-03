package com.beitu.saas.app.application.credit.pojo;

import org.apache.commons.lang3.StringUtils;

/**
 * 聚信立爬取成功回调数据
 */
public class JuxinliCallbackDataPojo {
    
    /**
     * 任务唯一性标识
     */
    private String taskId;
    
    /**
     * 任务查询凭证
     */
    private String token;
    
    /**
     * 平台机构或用户唯一标识
     */
    private String orgId;
    
    /**
     * 任务状态 true 成功 false 失败
     */
    private String success;
    
    /**
     * 数据源英文名
     */
    private String website;
    
    /**
     * 回调URL地址
     */
    private String callbackUrl;
    
    /**
     * 签名
     */
    private String sign;
    
    public String getTaskId() {
        return taskId;
    }
    
    public String getToken() {
        return token;
    }
    
    public String getOrgId() {
        return orgId;
    }
    
    public String getSuccess() {
        return success;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public String getCallbackUrl() {
        return callbackUrl;
    }
    
    public String getSign() {
        return sign;
    }
    
    public Boolean validate() {
        
        if (StringUtils.isEmpty(this.taskId) ||
                StringUtils.isEmpty(this.token) ||
                StringUtils.isEmpty(this.success) ||
                StringUtils.isEmpty(this.website) ||
                StringUtils.isEmpty(this.sign)) {
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }
}
