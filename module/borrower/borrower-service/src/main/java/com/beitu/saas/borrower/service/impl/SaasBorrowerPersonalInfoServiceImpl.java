package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerPersonalInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerPersonalInfoDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:46.996
*/
@Module(value = "SAAS借款人个人信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerPersonalInfoDaoImpl")
@Service
public class SaasBorrowerPersonalInfoServiceImpl extends AbstractBaseService implements SaasBorrowerPersonalInfoService {


    @Autowired
    private SaasBorrowerPersonalInfoDao saasBorrowerPersonalInfoDao;
}


