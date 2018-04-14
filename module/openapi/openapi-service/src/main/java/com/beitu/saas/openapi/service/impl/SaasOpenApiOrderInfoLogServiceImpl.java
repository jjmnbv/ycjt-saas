package com.beitu.saas.openapi.service.impl;

import com.beitu.saas.openapi.client.SaasOpenApiOrderInfoLogService;
import com.beitu.saas.openapi.dao.SaasOpenApiOrderInfoLogDao;
import com.beitu.saas.openapi.domain.SaasOpenApiOrderInfoLogVo;
import com.beitu.saas.openapi.entity.SaasOpenApiOrderInfoLog;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

import java.util.Date;

/**
 * User: linchengyu
 * Date: 2018-04-12
 * Time: 22:35:49.738
 */
@Module(value = "SAAS Open API 订单进件信息记录表服务模块")
@NameSpace("com.beitu.saas.openapi.dao.impl.SaasOpenApiOrderInfoLogDaoImpl")
@Service
public class SaasOpenApiOrderInfoLogServiceImpl extends AbstractBaseService implements SaasOpenApiOrderInfoLogService {
    
    @Autowired
    private SaasOpenApiOrderInfoLogDao saasOpenApiOrderInfoLogDao;
    
    @Override
    public Boolean addSaasOpenApiOrderInfoLog(SaasOpenApiOrderInfoLogVo vo) {
//        SaasOpenApiOrderInfoLog entity = saasOpenApiOrderInfoLogDao.insert(SaasOpenApiOrderInfoLogVo.convertVOToEntity(vo));
//        if (entity == null) {
//            return null;
//        }
//        SaasOpenApiOrderInfoLogVo saasOpenApiOrderInfoLogVo = new SaasOpenApiOrderInfoLogVo();
//        BeanUtils.copyProperties(entity, saasOpenApiOrderInfoLogVo);
//        saasOpenApiOrderInfoLogVo.setSaasOpenApiOrderInfoLogId(entity.getId());
//        return saasOpenApiOrderInfoLogVo;
        return saasOpenApiOrderInfoLogDao.insert(SaasOpenApiOrderInfoLogVo.convertVOToEntity(vo)) != null;
    }
    
    @Override
    public SaasOpenApiOrderInfoLogVo getByMobile(String mobile, Long from, Boolean success, Date startDate) {
        SaasOpenApiOrderInfoLog entity = saasOpenApiOrderInfoLogDao.getByMobile(mobile, from, success, startDate);
        if (entity == null) {
            return null;
        }
        SaasOpenApiOrderInfoLogVo saasOpenApiOrderInfoLogVo = new SaasOpenApiOrderInfoLogVo();
        BeanUtils.copyProperties(entity, saasOpenApiOrderInfoLogVo);
        saasOpenApiOrderInfoLogVo.setSaasOpenApiOrderInfoLogId(entity.getId());
        return saasOpenApiOrderInfoLogVo;
    }
}


