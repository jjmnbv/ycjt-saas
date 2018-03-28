package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.dao.SaasOrderStatusHistoryDao;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.879
 */
@Module(value = "SAAS订单状态流水表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderStatusHistoryDaoImpl")
@Service
public class SaasOrderStatusHistoryServiceImpl extends AbstractBaseService implements SaasOrderStatusHistoryService {

    @Autowired
    private SaasOrderStatusHistoryDao saasOrderStatusHistoryDao;

    @Override
    public String getLoanLendRemark(String orderNumb) {
        return null;
    }

    @Override
    public SaasOrderStatusHistory getOrderStatusHistoryByOrderNumb(String orderNumb) {
        return saasOrderStatusHistoryDao.selectOrderStatusHistoryByOrderNumb(orderNumb);
    }

}


