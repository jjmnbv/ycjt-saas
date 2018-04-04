package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerEmergentContactVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.976
 */
public interface SaasBorrowerEmergentContactService<T extends BaseEntity> extends BaseService<T> {

    int countByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    SaasBorrowerEmergentContactVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    Boolean updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode);

}