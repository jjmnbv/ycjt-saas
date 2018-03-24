package com.beitu.saas.borrower.dao.impl;

import com.beitu.saas.borrower.dao.SaasBorrowerIdentityInfoDao;
import com.beitu.saas.borrower.entity.SaasBorrowerIdentityInfo;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

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

}