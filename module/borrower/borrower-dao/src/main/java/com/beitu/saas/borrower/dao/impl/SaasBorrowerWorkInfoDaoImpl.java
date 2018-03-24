package com.beitu.saas.borrower.dao.impl;

import com.beitu.saas.borrower.dao.SaasBorrowerWorkInfoDao;
import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:47.016
 */

@Repository
public class SaasBorrowerWorkInfoDaoImpl extends AbstractBaseMapper<SaasBorrowerWorkInfo> implements SaasBorrowerWorkInfoDao {

    @Override
    public SaasBorrowerWorkInfo selectH5SaveInfoByBorrowerCode(String borrowerCode) {
        return this.getSqlSession().selectOne(this.getStatement(".selectH5SaveInfoByBorrowerCode"));
    }

}