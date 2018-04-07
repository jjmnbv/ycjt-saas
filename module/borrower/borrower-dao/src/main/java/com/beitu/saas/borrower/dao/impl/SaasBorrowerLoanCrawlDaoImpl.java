package com.beitu.saas.borrower.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.borrower.dao.SaasBorrowerLoanCrawlDao;
import com.beitu.saas.borrower.entity.SaasBorrowerLoanCrawl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: linchengyu
 * Date: 2018-04-04
 * Time: 16:09:15.747
 */

@Repository
public class SaasBorrowerLoanCrawlDaoImpl extends AbstractBaseMapper<SaasBorrowerLoanCrawl> implements SaasBorrowerLoanCrawlDao {

    @Override
    public List<SaasBorrowerLoanCrawl> selectByBorrowerCodeAndPlatform(String borrowerCode, Integer platform) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("platform", platform);
        return this.getSqlSession().selectList(this.getStatement(".selectByBorrowerCodeAndPlatform"), params);
    }

}