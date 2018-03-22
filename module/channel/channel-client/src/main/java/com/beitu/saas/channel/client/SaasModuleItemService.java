package com.beitu.saas.channel.client;

import com.beitu.saas.channel.entity.SaasModuleItemEntity;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.393
*/
public interface SaasModuleItemService<T extends BaseEntity> extends BaseService<T> {
    List<SaasModuleItemEntity> getSaasModuleItemEntityList(String moduleCode);

}