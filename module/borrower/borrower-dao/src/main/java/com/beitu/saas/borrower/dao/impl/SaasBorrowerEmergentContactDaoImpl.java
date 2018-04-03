package com.beitu.saas.borrower.dao.impl;

import com.beitu.saas.borrower.dao.SaasBorrowerEmergentContactDao;
import com.beitu.saas.borrower.entity.SaasBorrowerEmergentContact;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.969
 */

@Repository
public class SaasBorrowerEmergentContactDaoImpl extends AbstractBaseMapper<SaasBorrowerEmergentContact> implements SaasBorrowerEmergentContactDao {

    @Override
    public SaasBorrowerEmergentContact selectByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("orderNumb", orderNumb);
        return this.getSqlSession().selectOne(this.getStatement(".selectByBorrowerCodeAndOrderNumb"), params);
    }

    @Override
    public int countH5SaveInfoByBorrowerCode(String borrowerCode) {
        return this.getSqlSession().selectOne(this.getStatement(".countH5SaveInfoByBorrowerCode"), borrowerCode);
    }

    @Override
    public int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("borrowerCode", borrowerCode);
        return this.getSqlSession().update(this.getStatement(".updateOrderNumbByBorrowerCode"), params);
    }

}