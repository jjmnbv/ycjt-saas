package com.beitu.saas.finance.dao.impl;
import com.beitu.saas.finance.dao.SaasMerchantSmsInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.064
*/

@Repository
public class SaasMerchantSmsInfoDaoImpl extends AbstractBaseMapper<SaasMerchantSmsInfoEntity> implements SaasMerchantSmsInfoDao {
    @Override
    public SaasMerchantSmsInfoEntity selectSmsByMerchantCode(String merchantCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        return getSqlSession().selectOne(this.getStatement("selectSmsByMerchantCode"), map);
    }
}