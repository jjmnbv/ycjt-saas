package com.beitu.saas.openapi.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.openapi.entity.SaasOpenApiOrderInfoLog;

import java.util.Date;

/**
 * User: linchengyu
 * Date: 2018-04-12
 * Time: 22:35:49.630
 */

public interface SaasOpenApiOrderInfoLogDao extends BaseMapper<SaasOpenApiOrderInfoLog> {
    
    SaasOpenApiOrderInfoLog getByMobile(String mobile, Long from, Boolean success, Date startDate);
    
}