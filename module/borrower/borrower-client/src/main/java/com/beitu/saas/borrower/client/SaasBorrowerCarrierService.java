package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerCarrierVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.955
 */
public interface SaasBorrowerCarrierService<T extends BaseEntity> extends BaseService<T> {

    SaasBorrowerCarrierVo getByBorrowerCode(String borrowerCode);

    int countByBorrowerCode(String borrowerCode);

}