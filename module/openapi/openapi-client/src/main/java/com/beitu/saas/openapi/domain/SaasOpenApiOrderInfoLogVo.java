package com.beitu.saas.openapi.domain;

import com.beitu.saas.openapi.entity.SaasOpenApiOrderInfoLog;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: linchengyu
 * Date: 2018-04-12
 * Time: 22:35:49.741
 */
public class SaasOpenApiOrderInfoLogVo implements ResponseData, Serializable {
    
    private Long saasOpenApiOrderInfoLogId;
    
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 芝麻分
     */
    private Integer zmScore;
    /**
     * 身份证号码
     */
    private String identityNo;
    /**
     * 进件数据存储地址
     */
    private String dataUrl;
    /**
     * 流量类型
     */
    private Integer flowType;
    /**
     * 进件来源
     */
    private Long from;
    /**
     * 是否进件成功
     */
    private Boolean success;
    
    public SaasOpenApiOrderInfoLogVo() {
    }
    
    public SaasOpenApiOrderInfoLogVo(String mobile, Integer zmScore, String identityNo, String dataUrl, Integer flowType, Long from, Boolean success) {
        this.mobile = mobile;
        this.zmScore = zmScore;
        this.identityNo = identityNo;
        this.dataUrl = dataUrl;
        this.flowType = flowType;
        this.from = from;
        this.success = success;
    }
    
    public Long getSaasOpenApiOrderInfoLogId() {
        return saasOpenApiOrderInfoLogId;
    }
    
    public void setSaasOpenApiOrderInfoLogId(Long saasOpenApiOrderInfoLogId) {
        this.saasOpenApiOrderInfoLogId = saasOpenApiOrderInfoLogId;
    }
    
    
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Integer getZmScore() {
        return this.zmScore;
    }
    
    public void setZmScore(Integer zmScore) {
        this.zmScore = zmScore;
    }
    
    public String getIdentityNo() {
        return this.identityNo;
    }
    
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
    
    public String getDataUrl() {
        return this.dataUrl;
    }
    
    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
    
    public Integer getFlowType() {
        return this.flowType;
    }
    
    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }
    
    public Long getFrom() {
        return this.from;
    }
    
    public void setFrom(Long from) {
        this.from = from;
    }
    
    public Boolean getSuccess() {
        return this.success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public static SaasOpenApiOrderInfoLog convertVOToEntity(SaasOpenApiOrderInfoLogVo saasOpenApiOrderInfoLogVo) {
        if (saasOpenApiOrderInfoLogVo == null) {
            return null;
        }
        SaasOpenApiOrderInfoLog saasOpenApiOrderInfoLog = new SaasOpenApiOrderInfoLog();
        BeanUtils.copyProperties(saasOpenApiOrderInfoLogVo, saasOpenApiOrderInfoLog);
        saasOpenApiOrderInfoLog.setId(saasOpenApiOrderInfoLogVo.getSaasOpenApiOrderInfoLogId());
        return saasOpenApiOrderInfoLog;
    }
}
