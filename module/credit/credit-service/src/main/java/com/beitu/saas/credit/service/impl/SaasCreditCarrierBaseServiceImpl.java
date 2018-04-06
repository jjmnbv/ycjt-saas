package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditCarrierBaseService;
import com.beitu.saas.credit.dao.SaasCreditCarrierBaseDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.641
*/
@Module(value = "运营商报告基本资料表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierBaseDaoImpl")
@Service
public class SaasCreditCarrierBaseServiceImpl extends AbstractBaseService implements SaasCreditCarrierBaseService {


    @Autowired
    private SaasCreditCarrierBaseDao saasCreditCarrierBaseDao;
}


