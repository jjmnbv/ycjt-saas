package com.beitu.saas.finance.service.impl;

import com.beitu.saas.finance.client.SaasConsumeDayStatService;
import com.beitu.saas.finance.dao.SaasConsumeDayStatDao;
import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.372
 */
@Module(value = "消费日统计表服务模块")
@NameSpace("com.beitu.saas.finance.dao.impl.SaasConsumeDayStatDaoImpl")
@Service
public class SaasConsumeDayStatServiceImpl extends AbstractBaseService implements SaasConsumeDayStatService {


    @Autowired
    private SaasConsumeDayStatDao saasConsumeDayStatDao;

    @Override
    public SaasConsumeDayStatEntity getLastConsumeDayStatEntity() {
        return saasConsumeDayStatDao.getLastClearConsumeDayStat();
    }
}


