package com.beitu.saas.auth.dao;

import com.beitu.saas.auth.entity.SaasMerchantConfig;
import com.fqgj.common.base.BaseMapper;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.666
*/

public interface SaasMerchantConfigDao  extends BaseMapper<SaasMerchantConfig> {

    Integer updateByMerchantCode(SaasMerchantConfig record);
}