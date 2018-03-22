package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerRealInfoDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:47.005
*/
@Module(value = "SAAS借款人实名信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerRealInfoDaoImpl")
@Service
public class SaasBorrowerRealInfoServiceImpl extends AbstractBaseService implements SaasBorrowerRealInfoService {


    @Autowired
    private SaasBorrowerRealInfoDao saasBorrowerRealInfoDao;
}


