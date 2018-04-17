package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasMultiDebitService;
import com.beitu.saas.credit.dao.SaasMultiDebitDao;
import com.beitu.saas.credit.entity.SaasMultiDebitEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-04-16
 * Time: 21:04:02.128
 */
@Module(value = "优分多头信息表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasMultiDebitDaoImpl")
@Service
public class SaasMultiDebitServiceImpl extends AbstractBaseService implements SaasMultiDebitService {


    @Autowired
    private SaasMultiDebitDao saasMultiDebitDao;

    @Override
    public List<SaasMultiDebitEntity> getMultiDebitEntityList(String mobile) {
        return saasMultiDebitDao.selectMultiDebitEntityList(mobile);
    }
}


