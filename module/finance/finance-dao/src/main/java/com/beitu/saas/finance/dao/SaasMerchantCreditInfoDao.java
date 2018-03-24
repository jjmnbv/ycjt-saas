package com.beitu.saas.finance.dao;

import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.fqgj.common.base.BaseMapper;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.052
*/

public interface SaasMerchantCreditInfoDao  extends BaseMapper<SaasMerchantCreditInfoEntity> {

    SaasMerchantCreditInfoEntity selectCreditInfoByMerchantCode(String merchantCode);
}