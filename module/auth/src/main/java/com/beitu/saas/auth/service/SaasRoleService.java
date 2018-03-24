package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasRole;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.690
*/
public interface SaasRoleService<T extends BaseEntity> extends BaseService<T> {

    List<SaasRole> getRoleListByMerchantCode(String merchantCode, Page page);
}