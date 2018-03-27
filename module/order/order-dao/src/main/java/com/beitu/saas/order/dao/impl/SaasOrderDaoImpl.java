package com.beitu.saas.order.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.order.dao.SaasOrderDao;
import com.beitu.saas.order.entity.SaasOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.633
 */

@Repository
public class SaasOrderDaoImpl extends AbstractBaseMapper<SaasOrder> implements SaasOrderDao {

    @Override
    public int countByConditions(Map<String, Object> conditions) {
        return this.getSqlSession().selectOne(this.getStatement(".countByConditions"), conditions);
    }

    @Override
    public List<SaasOrder> selectByConditions(Map<String, Object> conditions) {
        return this.getSqlSession().selectList(this.getStatement(".selectByConditions"), conditions);
    }

}