package com.beitu.saas.borrower.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.borrower.dao.SaasBorrowerLoginLogDao;
import com.beitu.saas.borrower.entity.SaasBorrowerLoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-03
 * Time: 15:11:35.214
 */

@Repository
public class SaasBorrowerLoginLogDaoImpl extends AbstractBaseMapper<SaasBorrowerLoginLog> implements SaasBorrowerLoginLogDao {

    @Override
    public String selectPhoneSystemByBorrowerCode(String borrowerCode) {
        return this.getSqlSession().selectOne(this.getStatement(".selectPhoneSystemByBorrowerCode"), borrowerCode);
    }

    @Override
    public List<SaasBorrowerLoginLog> listBorrowerLivingArea(String borrowerCode) {
        return this.getSqlSession().selectList(this.getStatement(".listBorrowerLivingArea"), borrowerCode);
    }

}