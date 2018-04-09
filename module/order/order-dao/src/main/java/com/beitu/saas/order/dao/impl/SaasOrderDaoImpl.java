package com.beitu.saas.order.dao.impl;

import com.beitu.saas.order.dao.SaasOrderDao;
import com.beitu.saas.order.entity.SaasOrder;
import com.fqgj.common.base.AbstractBaseMapper;
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
    public SaasOrder selectByOrderNumbAndMerchantCode(String orderNumb, String merchantCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("merchantCode", merchantCode);
        return this.getSqlSession().selectOne(this.getStatement(".selectByOrderNumbAndMerchantCode"), params);
    }

    @Override
    public int updateOrderStatus(Map<String, Object> params) {
        return this.getSqlSession().update(this.getStatement(".updateOrderStatus"), params);
    }

    @Override
    public int updateOrderRemark(Map<String, Object> params) {
        return this.getSqlSession().update(this.getStatement(".updateOrderRemark"), params);
    }

    @Override
    public List<SaasOrder> selectByBorrowerCodeAndOrderStatusList(String borrowerCode, List<Integer> orderStatusList) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("orderStatusList", orderStatusList);
        return this.getSqlSession().selectList(this.getStatement(".selectByBorrowerCodeAndOrderStatusList"), params);
    }

    @Override
    public int updatePreliminaryReviewerCode(Long orderId, String operatorCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("id", orderId);
        params.put("preliminaryReviewerCode", operatorCode);
        return this.getSqlSession().update(this.getStatement(".updatePreliminaryReviewerCode"), params);
    }

    @Override
    public int updateFinalReviewerCode(Long orderId, String operatorCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("id", orderId);
        params.put("finalReviewerCode", operatorCode);
        return this.getSqlSession().update(this.getStatement(".updateFinalReviewerCode"), params);
    }

    @Override
    public List<String> selectOrderNumbByParams(String merchantCode, Integer orderStatus) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("merchantCode", merchantCode);
        params.put("orderStatus", orderStatus);
        return this.getSqlSession().selectList(this.getStatement(".selectOrderNumbByParams"), params);
    }

    @Override
    public SaasOrder selectMainSaasOrderByOrderNumb(String orderNumb) {
        return this.getSqlSession().selectOne(this.getStatement(".selectMainSaasOrderByOrderNumb"), orderNumb);
    }

}