package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerContactInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerContactInfoDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 16:43:06.900
 */
@Module(value = "SAAS借款人设备通讯录表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerContactInfoDaoImpl")
@Service
public class SaasBorrowerContactInfoServiceImpl extends AbstractBaseService implements SaasBorrowerContactInfoService {
    
    @Autowired
    private SaasBorrowerContactInfoDao saasBorrowerContactInfoDao;
}


