package com.beitu.saas.auth.service;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.655
*/
public interface SaasMerchantService<T extends BaseEntity> extends BaseService<T> {

    List<String> getMerchantList();

    SaasMerchantVo getByMerchantCode(String merchantCode);

}