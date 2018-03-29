package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.769
*/
public interface SaasAdminService<T extends BaseEntity> extends BaseService<T> {

    SaasAdmin login(String mobile, String paasword);

    SaasAdmin getSaasAdminByMoblie(String mobile);

    SaasAdmin getSaasAdminByAdminCode(String adminCode);

    List<SaasAdmin> getAdminListByMerchantCode(String adminCode, Page page);

    Boolean hasRegisteredMobile(String mobile);
}