package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.dao.SaasGxbEbDao;
import com.beitu.saas.credit.client.SaasGxbEbService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: xiaochong
* Date: 2018-04-17
* Time: 17:27:58.951
*/
@Module(value = "公信宝电商爬虫表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasGxbEbDaoImpl")
@Service
public class SaasGxbEbServiceImpl extends AbstractBaseService implements SaasGxbEbService {


    @Autowired
    private SaasGxbEbDao saasGxbEbDao;
}


