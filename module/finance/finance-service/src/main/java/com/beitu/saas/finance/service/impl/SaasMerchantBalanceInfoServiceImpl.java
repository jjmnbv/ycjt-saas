package com.beitu.saas.finance.service.impl;

import com.beitu.saas.finance.client.SaasMerchantBalanceInfoService;
import com.beitu.saas.finance.dao.SaasMerchantBalanceInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: fenqiguanjia
* Date: 2018-03-28
* Time: 11:41:55.210
*/
@Module(value = "机构账户余额表服务模块")
@NameSpace("com.beitu.saas.finance.dao.impl.SaasMerchantBalanceInfoDaoImpl")
@Service
public class SaasMerchantBalanceInfoServiceImpl extends AbstractBaseService implements SaasMerchantBalanceInfoService {


    @Autowired
    private SaasMerchantBalanceInfoDao saasMerchantBalanceInfoDao;

    @Override
    public SaasMerchantBalanceInfoEntity getMerchantBalanceInfoByMerchantCode(String merchantCode) {
        return saasMerchantBalanceInfoDao.selectMerchantBalanceInfoEntity(merchantCode);
    }
}


