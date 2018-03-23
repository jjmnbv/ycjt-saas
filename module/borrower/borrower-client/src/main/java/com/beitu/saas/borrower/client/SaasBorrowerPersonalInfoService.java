package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerPersonalInfoVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.996
 */
public interface SaasBorrowerPersonalInfoService<T extends BaseEntity> extends BaseService<T> {

    SaasBorrowerPersonalInfoVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

}