package com.beitu.saas.collection.service;

import com.beitu.saas.channel.client.SaasCollectionOrderService;
import com.beitu.saas.channel.domain.CollectionOrderQueryVo;
import com.beitu.saas.channel.enums.CollectionOrderStatusEnum;
import com.beitu.saas.channel.enums.OverdueTimeEnums;
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
@NameSpace("com.beitu.saas.collection.dao.impl.SaasCollectionOrderDaoImpl")
@Service
public class SaasCollectionOrderServiceImpl extends AbstractBaseService implements SaasCollectionOrderService {


    @Autowired
    private SaasCollectionOrderDao saasCollectionOrderDao;

    @Override
    public void createCollectionOrder(CollectionOrderInfoDetailVo collectionOrderInfoDetailVo) {
        SaasCollectionOrderEntity entity = new SaasCollectionOrderEntity();
        BeanUtils.copyProperties(collectionOrderInfoDetailVo, entity);
        saasCollectionOrderDao.insert(entity);
    }

    @Override
    public void closeOrder(String orderNo) {
        SaasCollectionOrderEntity entity = saasCollectionOrderDao.selectSaasCollectionOrderEntity(orderNo);
        entity.setStatus(CollectionOrderStatusEnum.CLOSE.getType());
        saasCollectionOrderDao.updateByPrimaryKey(entity);
    }

    @Override
    public List<CollectionOrderInfoDetailVo> getCollectionOrderListByPage(CollectionOrderQueryVo collectionOrderQueryVo, Page page) {
        if (null != collectionOrderQueryVo.getOverdueDaysType()) {
            OverdueTimeEnums timeEnums = OverdueTimeEnums.getEnum(collectionOrderQueryVo.getOverdueDaysType());
            if (null == timeEnums) {
                collectionOrderQueryVo.setOverdueStartDate(null);
                collectionOrderQueryVo.setOverdueEndDate(null);
            }
            collectionOrderQueryVo.setOverdueStartDate(DateUtil.getDate(DateUtil.addDate(new Date(), -timeEnums.getStart()), "yyyy-MM-dd"));
            collectionOrderQueryVo.setOverdueEndDate(DateUtil.getDate(DateUtil.addDate(new Date(), -timeEnums.getEnd()), "yyyy-MM-dd"));
        }

        // TODO: 2018/3/24 转换年龄和 备注
        return saasCollectionOrderDao.selectCollectionOrderListByPage(collectionOrderQueryVo, page);
    }
}


