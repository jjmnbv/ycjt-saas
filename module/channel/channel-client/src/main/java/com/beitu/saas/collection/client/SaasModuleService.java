package com.beitu.saas.collection.client;

import com.beitu.saas.collection.entity.SaasModuleEntity;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.383
*/
public interface SaasModuleService<T extends BaseEntity> extends BaseService<T> {

    List<SaasModuleEntity> getSaasModuleEntityList();
}