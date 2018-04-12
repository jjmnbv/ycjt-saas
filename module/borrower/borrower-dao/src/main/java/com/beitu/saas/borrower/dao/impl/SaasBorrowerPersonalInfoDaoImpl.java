package com.beitu.saas.borrower.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.borrower.dao.SaasBorrowerPersonalInfoDao;
import com.beitu.saas.borrower.entity.SaasBorrowerPersonalInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.991
 */

@Repository
public class SaasBorrowerPersonalInfoDaoImpl extends AbstractBaseMapper<SaasBorrowerPersonalInfo> implements SaasBorrowerPersonalInfoDao {

    @Override
    public SaasBorrowerPersonalInfo selectByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("orderNumb", orderNumb);
        return this.getSqlSession().selectOne(this.getStatement(".selectByBorrowerCodeAndOrderNumb"), params);
    }

    @Override
    public int countByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("orderNumb", orderNumb);
        return this.getSqlSession().selectOne(this.getStatement(".countByBorrowerCodeAndOrderNumb"), params);
    }

    @Override
    public int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("borrowerCode", borrowerCode);
        return this.getSqlSession().update(this.getStatement(".updateOrderNumbByBorrowerCode"), params);
    }

    @Override
    public Integer selectZmCreditScoreByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("orderNumb", orderNumb);
        return this.getSqlSession().selectOne(this.getStatement(".selectZmCreditScoreByBorrowerCodeAndOrderNumb"), params);
    }


    @Override
    public Integer selectRecentZmCreditScoreByBorrowerCode(String borrowerCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        return this.getSqlSession().selectOne(this.getStatement(".selectRecentZmCreditScoreByBorrowerCode"), params);    }
}