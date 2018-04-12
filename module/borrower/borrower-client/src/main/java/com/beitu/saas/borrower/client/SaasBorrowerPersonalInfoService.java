package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerPersonalInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerPersonalInfo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.996
 */
public interface SaasBorrowerPersonalInfoService<T extends BaseEntity> extends BaseService<T> {

    int countByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    SaasBorrowerPersonalInfoVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    Boolean updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode);

    Integer getZmCreditScoreByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    Integer getRecentZmCreditScoreByBorrowerCode(String borrowerCode);
}