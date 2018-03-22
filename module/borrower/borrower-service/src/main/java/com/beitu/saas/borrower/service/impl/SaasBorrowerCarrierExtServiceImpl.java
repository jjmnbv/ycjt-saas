package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerCarrierExtService;
import com.beitu.saas.borrower.dao.SaasBorrowerCarrierExtDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.966
*/
@Module(value = "SAAS借款人运营商报告扩充资料表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerCarrierExtDaoImpl")
@Service
public class SaasBorrowerCarrierExtServiceImpl extends AbstractBaseService implements SaasBorrowerCarrierExtService {


    @Autowired
    private SaasBorrowerCarrierExtDao saasBorrowerCarrierExtDao;
}


