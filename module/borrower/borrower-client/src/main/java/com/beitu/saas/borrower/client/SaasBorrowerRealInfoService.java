package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:47.004
 */
public interface SaasBorrowerRealInfoService<T extends BaseEntity> extends BaseService<T> {

    SaasBorrowerRealInfoVo getBorrowerRealInfoByBorrowerCode(String borrowerCode);

    SaasBorrowerRealInfoVo getBorrowerRealInfoByIdentityCodeAndMerchantCode(String identityCode, String merchantCode);

    List<SaasBorrowerRealInfoVo> listBorrowerRealInfoByNameAndMerchantCode(String name, String merchantCode);

    SaasBorrowerRealInfo create(String merchantCode, String borrowerCode, String name, String identityCode);

}