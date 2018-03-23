package com.beitu.saas.collection.dao.impl;
import com.beitu.saas.collection.dao.SaasCollectionOrderDao;
import com.beitu.saas.collection.entity.SaasCollectionOrderEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.361
*/

@Repository
public class SaasCollectionOrderDaoImpl extends AbstractBaseMapper<SaasCollectionOrderEntity> implements SaasCollectionOrderDao {
    @Override
    public SaasCollectionOrderEntity selectSaasCollectionOrderEntity(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        return getSqlSession().selectOne(this.getStatement("selectSaasCollectionOrderEntity"), map);
    }
}