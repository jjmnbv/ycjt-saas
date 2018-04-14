package com.beitu.saas.finance.dao.impl;
import com.beitu.saas.finance.dao.SaasMerchantBalanceInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
* User: fenqiguanjia
* Date: 2018-03-28
* Time: 11:41:55.201
*/

@Repository
public class SaasMerchantBalanceInfoDaoImpl extends AbstractBaseMapper<SaasMerchantBalanceInfoEntity> implements SaasMerchantBalanceInfoDao {

    @Override
    public SaasMerchantBalanceInfoEntity selectMerchantBalanceInfoEntity(String merchantCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        return getSqlSession().selectOne(this.getStatement("selectMerchantBalanceInfoEntity"), map);
    }

}