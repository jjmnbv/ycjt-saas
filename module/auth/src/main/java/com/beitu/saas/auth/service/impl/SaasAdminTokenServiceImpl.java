package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasAdminTokenDao;
import com.beitu.saas.auth.service.SaasAdminTokenService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.640
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasAdminTokenDaoImpl")
@Service
public class SaasAdminTokenServiceImpl extends AbstractBaseService implements SaasAdminTokenService {


    @Autowired
    private SaasAdminTokenDao saasAdminTokenDao;
}


