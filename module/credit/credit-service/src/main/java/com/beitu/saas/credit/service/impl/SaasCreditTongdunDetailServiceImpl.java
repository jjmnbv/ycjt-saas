package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditTongdunDetailService;
import com.beitu.saas.credit.dao.SaasCreditTongdunDetailDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.679
*/
@Module(value = "同盾信用记录详情表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditTongdunDetailDaoImpl")
@Service
public class SaasCreditTongdunDetailServiceImpl extends AbstractBaseService implements SaasCreditTongdunDetailService {


    @Autowired
    private SaasCreditTongdunDetailDao saasCreditTongdunDetailDao;
}


