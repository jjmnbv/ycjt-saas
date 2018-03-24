package com.beitu.saas.finance.dao.impl;
import com.beitu.saas.finance.dao.SaasMerchantCreditInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.053
*/

@Repository
public class SaasMerchantCreditInfoDaoImpl extends AbstractBaseMapper<SaasMerchantCreditInfoEntity> implements SaasMerchantCreditInfoDao {

    @Override
    public SaasMerchantCreditInfoEntity selectCreditInfoByMerchantCode(String merchantCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        return getSqlSession().selectOne(this.getStatement("selectCreditInfoByMerchantCode"), map);
    }
}