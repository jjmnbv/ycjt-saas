package com.beitu.saas.collection.dao.impl;

import com.beitu.saas.collection.dao.SaasModuleItemDao;
import com.beitu.saas.collection.entity.SaasModuleItemEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.391
 */

@Repository
public class SaasModuleItemDaoImpl extends AbstractBaseMapper<SaasModuleItemEntity> implements SaasModuleItemDao {

    @Override
    public List<SaasModuleItemEntity> selectModuleItemEntityList(String moduleCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("moduleCode", moduleCode);
        return getSqlSession().selectList(this.getStatement("selectModuleItemEntityList"), map);
    }
}