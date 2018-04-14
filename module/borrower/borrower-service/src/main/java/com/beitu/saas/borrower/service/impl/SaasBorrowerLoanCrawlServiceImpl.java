package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerLoanCrawlService;
import com.beitu.saas.borrower.dao.SaasBorrowerLoanCrawlDao;
import com.beitu.saas.borrower.domain.SaasBorrowerLoanCrawlVo;
import com.beitu.saas.borrower.entity.SaasBorrowerLoanCrawl;
import com.beitu.saas.common.utils.DateUtil;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

import java.util.List;

/**
 * User: linchengyu
 * Date: 2018-04-04
 * Time: 16:09:15.873
 */
@Module(value = "SAAS借款人借贷平台爬虫信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerLoanCrawlDaoImpl")
@Service
public class SaasBorrowerLoanCrawlServiceImpl extends AbstractBaseService implements SaasBorrowerLoanCrawlService {

    @Autowired
    private SaasBorrowerLoanCrawlDao saasBorrowerLoanCrawlDao;

    @Override
    public Boolean addSaasBorrowerLoanCrawl(SaasBorrowerLoanCrawlVo saasBorrowerLoanCrawlVo) {
        SaasBorrowerLoanCrawl entity = new SaasBorrowerLoanCrawl();
        BeanUtils.copyProperties(saasBorrowerLoanCrawlVo, entity);
        return create(entity) != null;
    }

    @Override
    public SaasBorrowerLoanCrawlVo getSaasBorrowerLoanCrawl(String borrowerCode, Integer platform) {
        List<SaasBorrowerLoanCrawl> saasBorrowerLoanCrawlList = saasBorrowerLoanCrawlDao.selectByBorrowerCodeAndPlatform(borrowerCode, platform);
        if (CollectionUtils.isEmpty(saasBorrowerLoanCrawlList)) {
            return null;
        }
        return SaasBorrowerLoanCrawlVo.convertEntityToVO(saasBorrowerLoanCrawlList.get(saasBorrowerLoanCrawlList.size() - 1));
    }

    @Override
    public Boolean effectivenessLoanCrawl(String borrowerCode, Integer platform) {
        List<SaasBorrowerLoanCrawl> saasBorrowerLoanCrawlList = saasBorrowerLoanCrawlDao.selectByBorrowerCodeAndPlatform(borrowerCode, platform);
        if (CollectionUtils.isEmpty(saasBorrowerLoanCrawlList)) {
            return Boolean.FALSE;
        }
        SaasBorrowerLoanCrawl entity = saasBorrowerLoanCrawlList.get(saasBorrowerLoanCrawlList.size() - 1);
        if (entity != null && DateUtil.isExceedOneMonth(entity.getGmtCreate())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}


