package com.beitu.saas.auth.dao.impl;

import com.beitu.saas.auth.dao.SaasAdminRoleDao;
import com.beitu.saas.auth.entity.SaasAdminRole;
import com.fqgj.common.base.AbstractBaseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.846
*/

@Repository
public class SaasAdminRoleDaoImpl extends AbstractBaseMapper<SaasAdminRole> implements SaasAdminRoleDao {


    @Override
    public Integer updateByAdminCode(SaasAdminRole record) {
        record.setGmtModified(new Date());
        record.setGmtCreate((Date)null);
        return Integer.valueOf(this.getSqlSession().update(this.getStatement(".updateByAdminCode"), record));
    }
}