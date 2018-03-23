package com.beitu.saas.collection.dao;

import com.beitu.saas.collection.entity.SaasCollectionOrderEntity;
import com.fqgj.common.base.BaseMapper;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.360
*/

public interface SaasCollectionOrderDao  extends BaseMapper<SaasCollectionOrderEntity> {
    SaasCollectionOrderEntity selectSaasCollectionOrderEntity(String orderNo);
}