package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasAdminRoleDao;
import com.beitu.saas.auth.service.SaasAdminRoleService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.847
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasAdminRoleDaoImpl")
@Service
public class SaasAdminRoleServiceImpl extends AbstractBaseService implements SaasAdminRoleService {


    @Autowired
    private SaasAdminRoleDao saasAdminRoleDao;
}


