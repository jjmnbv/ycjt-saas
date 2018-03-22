package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasMerchantConfigDao;
import com.beitu.saas.auth.service.SaasMerchantConfigService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.668
*/
@Module(value = "机构配置表服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasMerchantConfigDaoImpl")
@Service
public class SaasMerchantConfigServiceImpl extends AbstractBaseService implements SaasMerchantConfigService {


    @Autowired
    private SaasMerchantConfigDao saasMerchantConfigDao;
}


