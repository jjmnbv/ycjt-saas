package com.beitu.saas.openapi.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: linchengyu
 * Date: 2018-04-12
 * Time: 22:35:49.728
 * TableDesc:SAAS Open API 订单进件信息记录表
 */
public class SaasOpenApiOrderInfoLog extends BaseEntity {
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
    
    
    public String getMobile() {
        return this.mobile;
    }
    
    public SaasOpenApiOrderInfoLog setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    
    public Integer getZmScore() {
        return this.zmScore;
    }
    
    public SaasOpenApiOrderInfoLog setZmScore(Integer zmScore) {
        this.zmScore = zmScore;
        return this;
    }
    
    public String getIdentityNo() {
        return this.identityNo;
    }
    
    public SaasOpenApiOrderInfoLog setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
        return this;
    }
    
    public String getDataUrl() {
        return this.dataUrl;
    }
    
    public SaasOpenApiOrderInfoLog setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
        return this;
    }
    
    public Integer getFlowType() {
        return this.flowType;
    }
    
    public SaasOpenApiOrderInfoLog setFlowType(Integer flowType) {
        this.flowType = flowType;
        return this;
    }
    
    public Long getFrom() {
        return this.from;
    }
    
    public SaasOpenApiOrderInfoLog setFrom(Long from) {
        this.from = from;
        return this;
    }
    
    public Boolean getSuccess() {
        return this.success;
    }
    
    public SaasOpenApiOrderInfoLog setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
