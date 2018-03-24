package com.beitu.saas.finance.service.impl;

import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.dao.SaasMerchantCreditInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:11.056
 */
@Module(value = "机构点券表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasMerchantCreditInfoDaoImpl")
@Service
public class SaasMerchantCreditInfoServiceImpl extends AbstractBaseService implements SaasMerchantCreditInfoService {


    @Autowired
    private SaasMerchantCreditInfoDao saasMerchantCreditInfoDao;

    @Override
    public SaasMerchantCreditInfoEntity getCreditInfoByMerchantCode(String merchantCode) {
        return saasMerchantCreditInfoDao.selectCreditInfoByMerchantCode(merchantCode);
    }

    @Override
    public void update(SaasMerchantCreditInfoEntity saasMerchantCreditInfoEntity) {
        saasMerchantCreditInfoDao.updateByPrimaryKey(saasMerchantCreditInfoEntity);
    }
}


