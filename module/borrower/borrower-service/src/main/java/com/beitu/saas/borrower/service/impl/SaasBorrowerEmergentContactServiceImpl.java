package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerEmergentContactService;
import com.beitu.saas.borrower.dao.SaasBorrowerEmergentContactDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.977
*/
@Module(value = "SAAS借款人紧急联系人信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerEmergentContactDaoImpl")
@Service
public class SaasBorrowerEmergentContactServiceImpl extends AbstractBaseService implements SaasBorrowerEmergentContactService {


    @Autowired
    private SaasBorrowerEmergentContactDao saasBorrowerEmergentContactDao;
}


