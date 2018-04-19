package com.beitu.saas.borrower.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.borrower.entity.SaasBorrowerContactInfo;

import java.util.List;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 16:43:06.573
 */

public interface SaasBorrowerContactInfoDao extends BaseMapper<SaasBorrowerContactInfo> {
    
    SaasBorrowerContactInfo getByBorrowerCode(String merchantCode, String borrowerCode);
    
    List<SaasBorrowerContactInfo> getListByBorrowerCode(String merchantCode, String borrowerCode);
    
}