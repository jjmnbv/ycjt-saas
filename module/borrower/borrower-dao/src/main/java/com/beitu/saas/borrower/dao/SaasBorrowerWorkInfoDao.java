package com.beitu.saas.borrower.dao;

import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
import com.fqgj.common.base.BaseMapper;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:47.015
 */

public interface SaasBorrowerWorkInfoDao extends BaseMapper<SaasBorrowerWorkInfo> {

    SaasBorrowerWorkInfo selectByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    int countByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode);

}