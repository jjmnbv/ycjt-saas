package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerLoanCrawlService;
import com.beitu.saas.borrower.dao.SaasBorrowerLoanCrawlDao;
import com.beitu.saas.borrower.domain.SaasBorrowerLoanCrawlVo;
import com.beitu.saas.borrower.entity.SaasBorrowerLoanCrawl;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

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
}


