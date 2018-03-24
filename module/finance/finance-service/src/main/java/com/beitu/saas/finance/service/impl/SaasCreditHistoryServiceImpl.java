package com.beitu.saas.finance.service.impl;

import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.dao.SaasCreditHistoryDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:10.974
*/
@Module(value = "账户点券流水表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasCreditHistoryDaoImpl")
@Service
public class SaasCreditHistoryServiceImpl extends AbstractBaseService implements SaasCreditHistoryService {


    @Autowired
    private SaasCreditHistoryDao saasCreditHistoryDao;

    @Override
    public Long getYesterdayCreditStatCredit(String merchantCode, Date yesterday) {
        return saasCreditHistoryDao.selectYesterdayCreditStatCredit(merchantCode,yesterday);
    }
}


