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
    public SaasBorrowerPersonalInfo selectH5SaveInfoByBorrowerCode(String borrowerCode) {
        return this.getSqlSession().selectOne(this.getStatement(".selectH5SaveInfoByBorrowerCode"), borrowerCode);
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