package com.beitu.saas.borrower.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.borrower.entity.SaasBorrowerLoanCrawl;

import java.util.List;

/**
 * User: linchengyu
 * Date: 2018-04-04
 * Time: 16:09:15.742
 */

public interface SaasBorrowerLoanCrawlDao extends BaseMapper<SaasBorrowerLoanCrawl> {

    List<SaasBorrowerLoanCrawl> selectByBorrowerCodeAndPlatform(String borrowerCode, Integer platform);

}