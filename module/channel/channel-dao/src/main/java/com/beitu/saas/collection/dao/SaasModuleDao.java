package com.beitu.saas.collection.dao;

import com.beitu.saas.collection.entity.SaasModuleEntity;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.381
*/

public interface SaasModuleDao  extends BaseMapper<SaasModuleEntity> {

    List<SaasModuleEntity> selectModuleEntityList();
}