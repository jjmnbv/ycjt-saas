package com.beitu.saas.auth.dao;

import com.beitu.saas.auth.entity.SaasRolePermission;
import com.fqgj.common.base.BaseMapper;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.695
*/

public interface SaasRolePermissionDao  extends BaseMapper<SaasRolePermission> {

    void deleteByRoleId(Integer roleId);
}