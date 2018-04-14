package com.beitu.saas.openapi.dao.impl;

import com.beitu.saas.openapi.entity.SaasOpenApiOrderInfoLog;
import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.openapi.dao.SaasOpenApiOrderInfoLogDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: linchengyu
 * Date: 2018-04-12
 * Time: 22:35:49.633
 */

@Repository
public class SaasOpenApiOrderInfoLogDaoImpl extends AbstractBaseMapper<SaasOpenApiOrderInfoLog> implements SaasOpenApiOrderInfoLogDao {
    
    @Override
    public SaasOpenApiOrderInfoLog getByMobile(String mobile, Long from, Boolean success, Date startDate) {
        Map<String, Object> param = new HashMap();
        param.put("mobile", mobile);
        param.put("from", from);
        param.put("success", success);
        param.put("startDate", startDate);
        SaasOpenApiOrderInfoLog saasOpenApiOrderInfoLog = this.getSqlSession().selectOne(this.getStatement(".selectByMobile"), param);
        return saasOpenApiOrderInfoLog;
    }
    
}