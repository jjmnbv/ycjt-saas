package com.beitu.saas.borrower.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.borrower.entity.SaasBorrowerEmergentContact;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.969
 */

public interface SaasBorrowerEmergentContactDao extends BaseMapper<SaasBorrowerEmergentContact> {

    SaasBorrowerEmergentContact selectByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    int countH5SaveInfoByBorrowerCode(String borrowerCode);

    int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode);

}