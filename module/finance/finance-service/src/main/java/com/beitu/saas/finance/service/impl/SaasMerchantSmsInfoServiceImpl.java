package com.beitu.saas.finance.service.impl;

import com.beitu.saas.finance.client.SaasMerchantSmsInfoService;
import com.beitu.saas.finance.dao.SaasMerchantSmsInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.066
*/
@Module(value = "机构短信表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasMerchantSmsInfoDaoImpl")
@Service
public class SaasMerchantSmsInfoServiceImpl extends AbstractBaseService implements SaasMerchantSmsInfoService {


    @Autowired
    private SaasMerchantSmsInfoDao saasMerchantSmsInfoDao;

    @Override
    public SaasMerchantSmsInfoEntity getSmsInfoByMerchantCode(String merchantCode) {
        return saasMerchantSmsInfoDao.selectSmsByMerchantCode(merchantCode);
    }

    @Override
    public void update(SaasMerchantSmsInfoEntity saasMerchantSmsInfoEntity) {
        saasMerchantSmsInfoDao.updateByPrimaryKey(saasMerchantSmsInfoEntity);
    }
}


