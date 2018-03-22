package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerTokenService;
import com.beitu.saas.borrower.dao.SaasBorrowerTokenDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:47.013
*/
@Module(value = "SAAS借款人TOKEN表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerTokenDaoImpl")
@Service
public class SaasBorrowerTokenServiceImpl extends AbstractBaseService implements SaasBorrowerTokenService {

    @Autowired
    private SaasBorrowerTokenDao saasBorrowerTokenDao;

    @Override
    public String getBorrowerCodeByToken(String token) {
        return null;
    }

}


