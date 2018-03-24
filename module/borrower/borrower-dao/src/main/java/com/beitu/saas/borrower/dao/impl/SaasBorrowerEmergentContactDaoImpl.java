package com.beitu.saas.borrower.dao.impl;

import com.beitu.saas.borrower.dao.SaasBorrowerEmergentContactDao;
import com.beitu.saas.borrower.entity.SaasBorrowerEmergentContact;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.969
*/

@Repository
public class SaasBorrowerEmergentContactDaoImpl extends AbstractBaseMapper<SaasBorrowerEmergentContact> implements SaasBorrowerEmergentContactDao {

    @Override
    public SaasBorrowerEmergentContact selectH5SaveInfoByBorrowerCode(String borrowerCode) {
        return this.getSqlSession().selectOne(this.getStatement(".selectH5SaveInfoByBorrowerCode"));
    }

}