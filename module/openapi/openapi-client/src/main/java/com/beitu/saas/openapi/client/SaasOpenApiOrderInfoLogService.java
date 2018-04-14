package com.beitu.saas.openapi.client;

import com.beitu.saas.openapi.domain.SaasOpenApiOrderInfoLogVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
 * User: linchengyu
 * Date: 2018-04-12
 * Time: 22:35:49.733
 */
public interface SaasOpenApiOrderInfoLogService<T extends BaseEntity> extends BaseService<T> {
    
    Boolean addSaasOpenApiOrderInfoLog(SaasOpenApiOrderInfoLogVo vo);
    
    SaasOpenApiOrderInfoLogVo getByMobile(String mobile, Long from, Boolean success, Date startDate);
    
}