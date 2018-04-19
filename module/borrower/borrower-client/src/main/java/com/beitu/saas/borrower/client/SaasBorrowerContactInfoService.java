package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerContactInfoVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 16:43:06.884
 */
public interface SaasBorrowerContactInfoService<T extends BaseEntity> extends BaseService<T> {
    
    SaasBorrowerContactInfoVo getContactInfoByBorrowerCode(String merchantCode, String borrowerCode);
    
    Boolean addContactInfo(SaasBorrowerContactInfoVo saasBorrowerContactInfoVo);
    
}