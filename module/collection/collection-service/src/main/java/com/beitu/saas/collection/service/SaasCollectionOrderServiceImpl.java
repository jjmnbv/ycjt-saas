package com.beitu.saas.collection.service;

import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.collection.enums.CollectionOrderStatusEnum;
import com.beitu.saas.collection.enums.OverdueTimeEnums;
import com.beitu.saas.collection.param.CollectionOrderQueryParam;
import com.beitu.saas.collection.dao.SaasCollectionOrderDao;
import com.beitu.saas.collection.entity.SaasCollectionOrderEntity;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.362
 */
@Module(value = "订单表服务模块")
@NameSpace("SaasCollectionOrderDaoImpl")
@Service
public class SaasCollectionOrderServiceImpl extends AbstractBaseService implements SaasCollectionOrderService {


    @Autowired
    private SaasCollectionOrderDao saasCollectionOrderDao;

    @Override
    public void createCollectionOrder(String orderNo) {
        SaasCollectionOrderEntity entity = new SaasCollectionOrderEntity().setOrderNo(orderNo)
                .setStatus(CollectionOrderStatusEnum.OPEN.getType());
        saasCollectionOrderDao.insert(entity);
    }

    @Override
    public void closeOrder(String orderNo) {
        SaasCollectionOrderEntity entity = saasCollectionOrderDao.selectSaasCollectionOrderEntity(orderNo);
        entity.setStatus(CollectionOrderStatusEnum.CLOSE.getType());
        saasCollectionOrderDao.updateByPrimaryKey(entity);
    }

    @Override
    public List<CollectionOrderInfoDetailVo> getCollectionOrderListByPage(CollectionOrderQueryParam collectionOrderQueryParam, Page page) {
        if (null != collectionOrderQueryParam.getOverdueDaysType()) {
            OverdueTimeEnums timeEnums = OverdueTimeEnums.getEnum(collectionOrderQueryParam.getOverdueDaysType());
            if (null != timeEnums) {
                collectionOrderQueryParam.setOverdueStartDate(DateUtil.getDate(DateUtil.addDate(new Date(), -timeEnums.getEnd()), "yyyy-MM-dd"));
                collectionOrderQueryParam.setOverdueEndDate(DateUtil.getDate(DateUtil.addDate(new Date(), -timeEnums.getStart()), "yyyy-MM-dd"));

            }
        }

        return saasCollectionOrderDao.selectCollectionOrderListByPage(collectionOrderQueryParam, page);
    }
}


