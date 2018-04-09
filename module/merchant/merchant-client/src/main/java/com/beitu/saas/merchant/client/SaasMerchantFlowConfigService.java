package com.beitu.saas.merchant.client;
import com.beitu.saas.merchant.client.domain.SaasMerchantFlowConfigVo;
import com.beitu.saas.merchant.entity.SaasMerchantFlowConfig;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-04-09
* Time: 11:49:34.714
*/
public interface SaasMerchantFlowConfigService<T extends BaseEntity> extends BaseService<T> {

    SaasMerchantFlowConfigVo getMerchantFlowConfigByMerchantCode(String merchantCode);


    void replace(SaasMerchantFlowConfigVo record);
}