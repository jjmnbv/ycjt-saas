package com.beitu.saas.collection.service;

import com.beitu.saas.channel.client.SaasCollectionOrderService;
import com.beitu.saas.channel.domain.OrderInfoVo;
import com.beitu.saas.channel.enums.CollectionOrderStatusEnum;
import com.beitu.saas.collection.dao.SaasCollectionOrderDao;
import com.beitu.saas.collection.entity.SaasCollectionOrderEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.362
 */
@Module(value = "订单表服务模块")
@NameSpace("com.beitu.saas.collection.dao.impl.SaasCollectionOrderDaoImpl")
@Service
public class SaasCollectionOrderServiceImpl extends AbstractBaseService implements SaasCollectionOrderService {


    @Autowired
    private SaasCollectionOrderDao saasCollectionOrderDao;

    @Override
    public void createCollectionOrder(OrderInfoVo orderInfoVo) {
        SaasCollectionOrderEntity entity = new SaasCollectionOrderEntity();
        BeanUtils.copyProperties(orderInfoVo, entity);
        saasCollectionOrderDao.insert(entity);
    }

    @Override
    public void closeOrder(String orderNo) {
        SaasCollectionOrderEntity entity = saasCollectionOrderDao.selectSaasCollectionOrderEntity(orderNo);
        entity.setStatus(CollectionOrderStatusEnum.CLOSE.getType());
        saasCollectionOrderDao.updateByPrimaryKey(entity);
    }
}


