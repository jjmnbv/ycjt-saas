package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditCarrierBillService;
import com.beitu.saas.credit.dao.SaasCreditCarrierBillDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.647
*/
@Module(value = "运营商报告账单详情表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierBillDaoImpl")
@Service
public class SaasCreditCarrierBillServiceImpl extends AbstractBaseService implements SaasCreditCarrierBillService {


    @Autowired
    private SaasCreditCarrierBillDao saasCreditCarrierBillDao;
}


