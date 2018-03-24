package com.beitu.saas.finance.client;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.054
*/
public interface SaasMerchantCreditInfoService<T extends BaseEntity> extends BaseService<T> {

    SaasMerchantCreditInfoEntity getCreditInfoByMerchantCode(String merchantCode);

    void update(SaasMerchantCreditInfoEntity saasMerchantCreditInfoEntity);
}