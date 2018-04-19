package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerGpsLogVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 14:04:03.574
 */
public interface SaasBorrowerGpsLogService<T extends BaseEntity> extends BaseService<T> {
    
    Boolean batchAddSaasBorrowerGpsLogVo(List<SaasBorrowerGpsLogVo> saasBorrowerGpsLogVoList);
    
    Date getLastDateByBorrowerCodeAndMerchantCode(String merchantCode, String borrowerCode);
    
    List<SaasBorrowerGpsLogVo> listByBorrowerCodeAndMerchantCode(String merchantCode, String borrowerCode);
    
}