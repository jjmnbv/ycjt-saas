package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrower;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.936
 */
public interface SaasBorrowerService<T extends BaseEntity> extends BaseService<T> {

    SaasBorrowerVo getByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode);

    SaasBorrowerVo getByMobileAndMerchantCode(String mobile, String merchantCode);

    SaasBorrower create(SaasBorrowerVo saasBorrowerVo);

    String getMobileByBorrowerCode(String borrowerCode);

    Boolean isSaasBorrower(String userCode);

}