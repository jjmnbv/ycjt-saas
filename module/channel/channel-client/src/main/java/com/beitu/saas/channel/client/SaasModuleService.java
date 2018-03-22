package com.beitu.saas.channel.client;

import com.beitu.saas.channel.entity.SaasModuleEntity;
import com.beitu.saas.channel.entity.SaasModuleItemEntity;
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