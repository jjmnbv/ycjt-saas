package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
 * User: linchengyu
 * Date: 2018-04-04
 * Time: 16:09:15.864
 * TableDesc:SAAS借款人借贷平台爬虫信息表
 */
public class SaasBorrowerLoanCrawl extends BaseEntity {
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 任务唯一性标识
     */
    private String taskId;
    /**
     * 任务查询凭证
     */
    private String token;
    /**
     * 借款平台类型
     */
    private Integer platform;
    /**
     * 借贷平台爬虫数据存储地址
     */
    private String url;
    
    
    public String getBorrowerCode() {
        return this.borrowerCode;
    }
    
    public SaasBorrowerLoanCrawl setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }
    
    public String getTaskId() {
        return this.taskId;
    }
    
    public SaasBorrowerLoanCrawl setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public SaasBorrowerLoanCrawl setToken(String token) {
        this.token = token;
        return this;
    }
    
    public Integer getPlatform() {
        return this.platform;
    }
    
    public SaasBorrowerLoanCrawl setPlatform(Integer platform) {
        this.platform = platform;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public SaasBorrowerLoanCrawl setUrl(String url) {
        this.url = url;
        return this;
    }
}
