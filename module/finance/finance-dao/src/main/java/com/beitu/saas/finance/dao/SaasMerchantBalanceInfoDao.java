package com.beitu.saas.finance.dao;

import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
import com.fqgj.common.base.BaseMapper;

/**
* User: fenqiguanjia
* Date: 2018-03-28
* Time: 11:41:55.197
*/

public interface SaasMerchantBalanceInfoDao extends BaseMapper<SaasMerchantBalanceInfoEntity> {
    SaasMerchantBalanceInfoEntity selectMerchantBalanceInfoEntity(String merchantCode);
}