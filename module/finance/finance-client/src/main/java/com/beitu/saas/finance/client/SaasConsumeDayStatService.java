package com.beitu.saas.finance.client;

import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.371
 */
public interface SaasConsumeDayStatService<T extends BaseEntity> extends BaseService<T> {

    SaasConsumeDayStatEntity getLastConsumeDayStatEntity(String merchantCode);
}