package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.entity.SaasBorrowerToken;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:47.012
 */
public interface SaasBorrowerTokenService<T extends BaseEntity> extends BaseService<T> {

    String getBorrowerCodeByToken(String token);

    SaasBorrowerToken create(String borrowerCode, String merchantCode);

    SaasBorrowerToken refreshToken(String borrowerCode, String merchantCode);

}