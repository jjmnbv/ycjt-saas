package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasMerchantDao;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.656
*/
@Module(value = "机构表服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasMerchantDaoImpl")
@Service
public class SaasMerchantServiceImpl extends AbstractBaseService implements SaasMerchantService {


    @Autowired
    private SaasMerchantDao saasMerchantDao;
}


