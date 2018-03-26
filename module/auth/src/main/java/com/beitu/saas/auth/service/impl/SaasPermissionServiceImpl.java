package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasPermissionDao;
import com.beitu.saas.auth.service.SaasPermissionService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.683
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasPermissionDaoImpl")
@Service
public class SaasPermissionServiceImpl extends AbstractBaseService implements SaasPermissionService {


    @Autowired
    private SaasPermissionDao saasPermissionDao;
}


