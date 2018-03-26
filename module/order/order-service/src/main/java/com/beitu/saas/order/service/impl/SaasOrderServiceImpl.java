package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.dao.SaasOrderDao;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.749
 */
@Module(value = "SAAS订单表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderDaoImpl")
@Service
public class SaasOrderServiceImpl extends AbstractBaseService implements SaasOrderService {

    @Autowired
    private SaasOrderDao saasOrderDao;

    @Override
    public List<SaasOrderVo> listByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode) {
        return null;
    }

    @Override
    public OrderStatusEnum getOrderStatusByOrderNumb(String orderNumb) {
        return null;
    }

    @Override
    public List<SaasOrderVo> listEffectiveOrderByOrderNumb(String orderNumb) {
        return null;
    }

    @Override
    public Boolean isReviewRefuse(String borrowerCode, String channelCode) {
        return Boolean.FALSE;
    }

}