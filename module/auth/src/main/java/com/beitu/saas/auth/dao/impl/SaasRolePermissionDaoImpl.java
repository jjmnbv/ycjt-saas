package com.beitu.saas.auth.dao.impl;

import com.beitu.saas.auth.dao.SaasRolePermissionDao;
import com.beitu.saas.auth.entity.SaasRolePermission;
import com.fqgj.common.base.AbstractBaseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.695
*/

@Repository
public class SaasRolePermissionDaoImpl extends AbstractBaseMapper<SaasRolePermission> implements SaasRolePermissionDao {

    @Override
    public void deleteByRoleId(Integer roleId){
        this.getSqlSession().update(this.getStatement("deleteByRoleId"),roleId);
    }

    @Override
    public SaasRolePermission replace(SaasRolePermission record) {
        record.setGmtModified(new Date());
        this.getSqlSession().insert(this.getStatement(".replace"), record);
        return record;
    }
}