package com.beitu.saas.channel.dao.impl;
import com.beitu.saas.channel.dao.SaasModuleDao;
import com.beitu.saas.channel.entity.SaasModuleEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.382
*/

@Repository
public class SaasModuleDaoImpl extends AbstractBaseMapper<SaasModuleEntity> implements SaasModuleDao {

    @Override
    public List<SaasModuleEntity> selectModuleEntityList() {
        return getSqlSession().selectList(this.getStatement("selectModuleEntityList"));
    }
}