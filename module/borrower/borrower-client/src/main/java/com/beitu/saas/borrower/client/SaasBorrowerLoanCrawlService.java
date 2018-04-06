package com.beitu.saas.borrower.client;

import com.beitu.saas.borrower.domain.SaasBorrowerLoanCrawlVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: linchengyu
 * Date: 2018-04-04
 * Time: 16:09:15.869
 */
public interface SaasBorrowerLoanCrawlService<T extends BaseEntity> extends BaseService<T> {
    
    Boolean addSaasBorrowerLoanCrawl(SaasBorrowerLoanCrawlVo saasBorrowerLoanCrawlVo);
    
    SaasBorrowerLoanCrawlVo getSaasBorrowerLoanCrawl(String borrowerCode, Integer platform);
    
}