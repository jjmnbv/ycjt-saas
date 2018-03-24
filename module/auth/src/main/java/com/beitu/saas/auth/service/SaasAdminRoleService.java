package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasAdminRole;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.847
*/
public interface SaasAdminRoleService<T extends BaseEntity> extends BaseService<T> {

    Integer updateByAdminCode(SaasAdminRole record);
}