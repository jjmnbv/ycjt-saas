package com.beitu.saas.borrower.dao;

import com.beitu.saas.borrower.entity.SaasBorrowerPersonalInfo;
import com.fqgj.common.base.BaseMapper;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.990
 */

public interface SaasBorrowerPersonalInfoDao extends BaseMapper<SaasBorrowerPersonalInfo> {

    SaasBorrowerPersonalInfo selectH5SaveInfoByBorrowerCode(String borrowerCode);

    int countH5SaveInfoByBorrowerCode(String borrowerCode);

    int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode);

}