package com.beitu.saas.finance.dao;

import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.fqgj.common.base.BaseMapper;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.063
*/

public interface SaasMerchantSmsInfoDao  extends BaseMapper<SaasMerchantSmsInfoEntity> {
    SaasMerchantSmsInfoEntity selectSmsByMerchantCode(String merchantCode);

}