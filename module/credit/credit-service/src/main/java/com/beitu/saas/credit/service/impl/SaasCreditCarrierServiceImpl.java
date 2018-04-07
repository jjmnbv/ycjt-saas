package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.dao.SaasCreditCarrierDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierVo;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.628
*/
@Module(value = "运营商报告查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierDaoImpl")
@Service
public class SaasCreditCarrierServiceImpl extends AbstractBaseService implements SaasCreditCarrierService {

    @Autowired
    private SaasCreditCarrierDao saasCreditCarrierDao;

    @Override
    public SaasCreditCarrier addSaasCreditCarrier(SaasCreditCarrierVo saasCreditCarrierVo) {
        return null;
    }

    @Override
    public SaasCreditCarrierVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        return null;
    }

    @Override
    public Boolean updateSuccess(Long id) {
        return null;
    }
}


