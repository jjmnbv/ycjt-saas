package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.dao.SaasOrderDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.749
 */
@Module(value = "SAAS订单表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderDaoImpl")
@Service
public class SaasOrderServiceImpl extends AbstractBaseService implements BaseService {

    @Autowired
    private SaasOrderDao saasOrderDao;
}


