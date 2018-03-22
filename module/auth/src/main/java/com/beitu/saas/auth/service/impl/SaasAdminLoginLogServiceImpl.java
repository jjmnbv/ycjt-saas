package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasAdminLoginLogDao;
import com.beitu.saas.auth.service.SaasAdminLoginLogService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.839
*/
@Module(value = "管理员登录日志表服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasAdminLoginLogDaoImpl")
@Service
public class SaasAdminLoginLogServiceImpl extends AbstractBaseService implements SaasAdminLoginLogService {


    @Autowired
    private SaasAdminLoginLogDao saasAdminLoginLogDao;
}


