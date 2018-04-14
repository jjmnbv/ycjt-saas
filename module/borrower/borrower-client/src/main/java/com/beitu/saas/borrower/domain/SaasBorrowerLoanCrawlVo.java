package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerLoanCrawl;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: linchengyu
 * Date: 2018-04-04
 * Time: 16:09:15.882
 */
public class SaasBorrowerLoanCrawlVo implements ResponseData, Serializable {
    
    private Long saasBorrowerLoanCrawlId;
    
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
    
    public SaasBorrowerLoanCrawlVo() {
    }
    
    public SaasBorrowerLoanCrawlVo(String borrowerCode, String taskId, String token, Integer platform, String url) {
        this.borrowerCode = borrowerCode;
        this.taskId = taskId;
        this.token = token;
        this.platform = platform;
        this.url = url;
    }
    
    public Long getSaasBorrowerLoanCrawlId() {
        return saasBorrowerLoanCrawlId;
    }
    
    public void setSaasBorrowerLoanCrawlId(Long saasBorrowerLoanCrawlId) {
        this.saasBorrowerLoanCrawlId = saasBorrowerLoanCrawlId;
    }
    
    
    public String getBorrowerCode() {
        return this.borrowerCode;
    }
    
    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }
    
    public String getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public Integer getPlatform() {
        return this.platform;
    }
    
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public static SaasBorrowerLoanCrawlVo convertEntityToVO(SaasBorrowerLoanCrawl entity) {
        if (entity == null) {
            return null;
        }
        SaasBorrowerLoanCrawlVo vo = new SaasBorrowerLoanCrawlVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setSaasBorrowerLoanCrawlId(entity.getId());
        return vo;
    }
}
