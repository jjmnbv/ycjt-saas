package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasMerchantConfig;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.667
*/
public interface SaasMerchantConfigService<T extends BaseEntity> extends BaseService<T> {

    Boolean isCompanyContractByMerchantCode(String merchantCode);

    List<Integer> getSmsConfigByMerchantCode(String merchantCode);

    Boolean updateByMerchantCode(SaasMerchantConfig record);
}