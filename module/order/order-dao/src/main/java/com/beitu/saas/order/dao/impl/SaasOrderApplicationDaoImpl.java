package com.beitu.saas.order.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.order.dao.SaasOrderApplicationDao;
import com.beitu.saas.order.entity.SaasOrderApplication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.760
 */

@Repository
public class SaasOrderApplicationDaoImpl extends AbstractBaseMapper<SaasOrderApplication> implements SaasOrderApplicationDao {

    @Override
    public SaasOrderApplication selectByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("orderNumb", orderNumb);
        return this.getSqlSession().selectOne(this.getStatement(".selectByBorrowerCodeAndOrderNumb"), params);
    }

    @Override
    public int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("borrowerCode", borrowerCode);
        return this.getSqlSession().update(this.getStatement(".updateOrderNumbByBorrowerCode"), borrowerCode);
    }

}