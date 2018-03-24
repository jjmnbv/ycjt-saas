package com.beitu.saas.finance.service.impl;

import com.beitu.saas.finance.client.SaasSmsHistoryService;
import com.beitu.saas.finance.dao.SaasSmsHistoryDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:11.076
 */
@Module(value = "账户短信条数流水表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasSmsHistoryDaoImpl")
@Service
public class SaasSmsHistoryServiceImpl extends AbstractBaseService implements SaasSmsHistoryService {


    @Autowired
    private SaasSmsHistoryDao saasSmsHistoryDao;

    @Override
    public Long getYesterdaySmsStatCredit(String merchantCode, Date yesterday) {
        return saasSmsHistoryDao.selectYesterdaySmsStatCredit(merchantCode, yesterday);
    }
}


