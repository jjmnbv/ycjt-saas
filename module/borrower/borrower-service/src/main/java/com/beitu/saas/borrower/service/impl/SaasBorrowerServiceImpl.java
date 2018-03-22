package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.dao.SaasBorrowerDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.944
*/
@Module(value = "SAAS借款人表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerDaoImpl")
@Service
public class SaasBorrowerServiceImpl extends AbstractBaseService implements SaasBorrowerService {


    @Autowired
    private SaasBorrowerDao saasBorrowerDao;
}


