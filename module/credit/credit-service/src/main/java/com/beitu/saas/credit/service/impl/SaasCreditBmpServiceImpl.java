package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditBmpService;
import com.beitu.saas.credit.dao.SaasCreditBmpDao;
import com.beitu.saas.credit.domain.SaasCreditBmpVo;
import com.beitu.saas.credit.entity.SaasCreditBmp;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.604
*/
@Module(value = "电话邦匹配数据查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditBmpDaoImpl")
@Service
public class SaasCreditBmpServiceImpl extends AbstractBaseService implements SaasCreditBmpService {

    @Autowired
    private SaasCreditBmpDao saasCreditBmpDao;

    @Override
    public SaasCreditBmp addSaasCreditBmp(SaasCreditBmpVo saasCreditBmpVo) {
        return null;
    }

    @Override
    public SaasCreditBmpVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        return null;
    }

    @Override
    public Boolean updateSuccess(Long id) {
        return null;
    }
}


