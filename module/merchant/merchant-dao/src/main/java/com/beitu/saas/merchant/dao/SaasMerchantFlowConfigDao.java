package com.beitu.saas.merchant.dao;

import com.beitu.saas.merchant.entity.SaasMerchantFlowConfig;
import com.fqgj.common.base.BaseMapper;

/**
* User: xiaochong
* Date: 2018-04-09
* Time: 11:49:34.708
*/

public interface SaasMerchantFlowConfigDao extends BaseMapper<SaasMerchantFlowConfig> {

    SaasMerchantFlowConfig replace(SaasMerchantFlowConfig record);
}