package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerCarrierService;
import com.beitu.saas.borrower.dao.SaasBorrowerCarrierDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.956
*/
@Module(value = "SAAS借款人运营商报告信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerCarrierDaoImpl")
@Service
public class SaasBorrowerCarrierServiceImpl extends AbstractBaseService implements SaasBorrowerCarrierService {


    @Autowired
    private SaasBorrowerCarrierDao saasBorrowerCarrierDao;
}


