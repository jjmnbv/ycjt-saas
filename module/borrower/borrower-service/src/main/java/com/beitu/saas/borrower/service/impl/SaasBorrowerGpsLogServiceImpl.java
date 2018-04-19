package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerGpsLogService;
import com.beitu.saas.borrower.dao.SaasBorrowerGpsLogDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 14:04:03.578
 */
@Module(value = "借款人GPS信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerGpsLogDaoImpl")
@Service
public class SaasBorrowerGpsLogServiceImpl extends AbstractBaseService implements SaasBorrowerGpsLogService {
    
    @Autowired
    private SaasBorrowerGpsLogDao saasBorrowerGpsLogDao;
    
}


