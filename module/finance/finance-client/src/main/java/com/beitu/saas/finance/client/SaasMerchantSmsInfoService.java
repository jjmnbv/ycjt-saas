package com.beitu.saas.finance.client;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.065
*/
public interface SaasMerchantSmsInfoService<T extends BaseEntity> extends BaseService<T> {

    SaasMerchantSmsInfoEntity getSmsInfoByMerchantCode(String merchantCode);

    void update(SaasMerchantSmsInfoEntity saasMerchantSmsInfoEntity);


    Boolean increase(String merchantCode, Long value);

    Boolean decrease(String merchantCode, Long value);

    Long getSmsByMerchantCode(String merchantCode);
}