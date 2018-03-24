package com.beitu.saas.borrower.dao.impl;

import com.beitu.saas.borrower.dao.SaasBorrowerIdentityInfoDao;
import com.beitu.saas.borrower.entity.SaasBorrowerIdentityInfo;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.980
*/

@Repository
public class SaasBorrowerIdentityInfoDaoImpl extends AbstractBaseMapper<SaasBorrowerIdentityInfo> implements SaasBorrowerIdentityInfoDao {

    @Override
    public SaasBorrowerIdentityInfo selectH5SaveInfoByBorrowerCode(String borrowerCode) {
        return this.getSqlSession().selectOne(this.getStatement(".selectH5SaveInfoByBorrowerCode"));
    }

    @Override
    public int countH5SaveInfoByBorrowerCode(String borrowerCode) {
        return this.getSqlSession().selectOne(this.getStatement(".countH5SaveInfoByBorrowerCode"));
    }

    @Override
    public int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("borrowerCode", borrowerCode);
        return this.getSqlSession().update(this.getStatement(".updateOrderNumbByBorrowerCode"), params);
    }

}