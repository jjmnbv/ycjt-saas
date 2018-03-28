package com.beitu.saas.order.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.order.dao.SaasOrderDao;
import com.beitu.saas.order.entity.SaasOrder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    @Override
    public SaasOrder selectByBorrowerCodeAndChannelCode(String borrowerCode, String channelCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("channelCode", channelCode);
        return this.getSqlSession().selectOne(this.getStatement(".selectByBorrowerCodeAndChannelCode"), params);
    }

    @Override
    public SaasOrder selectByOrderNumb(String orderNumb) {
        return this.getSqlSession().selectOne(this.getStatement(".selectByOrderNumb"), orderNumb);
    }

    @Override
    public int updateOrderStatus(Map<String, Object> params) {
        return this.getSqlSession().update(this.getStatement(".updateOrderStatus"), params);
    }
}