package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditTongdunService;
import com.beitu.saas.credit.dao.SaasCreditTongdunDao;
import com.beitu.saas.credit.domain.SaasCreditTongdunVo;
import com.beitu.saas.credit.entity.SaasCreditTongdun;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.673
*/
@Module(value = "SAAS同盾信用记录查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditTongdunDaoImpl")
@Service
public class SaasCreditTongdunServiceImpl extends AbstractBaseService implements SaasCreditTongdunService {

    @Autowired
    private SaasCreditTongdunDao saasCreditTongdunDao;

    @Override
    public SaasCreditTongdun addSaasCreditTongdun(SaasCreditTongdunVo saasCreditTongdunVo) {
        return null;
    }

    @Override
    public SaasCreditTongdunVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        return null;
    }

    @Override
    public SaasCreditTongdunVo getByMobileAndIdentityCode(String mobile, String identityCode) {
        return null;
    }

    @Override
    public Boolean updateSuccess(Long id) {
        return null;
    }
}


