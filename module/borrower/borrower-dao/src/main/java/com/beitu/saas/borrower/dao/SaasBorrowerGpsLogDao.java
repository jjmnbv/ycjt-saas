package com.beitu.saas.borrower.dao;

import com.beitu.saas.borrower.entity.SaasBorrowerGpsLog;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 14:04:03.474
 */

public interface SaasBorrowerGpsLogDao extends BaseMapper<SaasBorrowerGpsLog> {
    
    Integer batchAddSaasBorrowerGpsLog(List<SaasBorrowerGpsLog> saasBorrowerGpsLogList);
    
    List<SaasBorrowerGpsLog> selectByBorrowerCodeAndMerchantCode(String merchantCode, String borrowerCode, Integer limit);
    
}