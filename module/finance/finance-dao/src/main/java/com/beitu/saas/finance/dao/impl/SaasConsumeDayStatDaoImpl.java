package com.beitu.saas.finance.dao.impl;

import com.beitu.saas.finance.dao.SaasConsumeDayStatDao;
import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.371
 */

@Repository
public class SaasConsumeDayStatDaoImpl extends AbstractBaseMapper<SaasConsumeDayStatEntity> implements SaasConsumeDayStatDao {

    @Override
    public SaasConsumeDayStatEntity getLastClearConsumeDayStat(String merchantCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        return getSqlSession().selectOne(this.getStatement("getLastClearConsumeDayStat"), map);
    }
}