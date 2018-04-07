package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditDunningService;
import com.beitu.saas.credit.dao.SaasCreditDunningDao;
import com.beitu.saas.credit.domain.SaasCreditDunningVo;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.662
 */
@Module(value = "用户催收数据查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditDunningDaoImpl")
@Service
public class SaasCreditDunningServiceImpl extends AbstractBaseService implements SaasCreditDunningService {

    @Autowired
    private SaasCreditDunningDao saasCreditDunningDao;

    @Override
    public SaasCreditDunningVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        return null;
    }

    @Override
    public SaasCreditDunning addSaasCreditDunning(SaasCreditDunningVo saasCreditDunningVo) {
        return null;
    }

    @Override
    public Boolean updateSuccess(Long id) {
        SaasCreditDunning saasCreditDunning = new SaasCreditDunning();
        saasCreditDunning.setSuccess(Boolean.TRUE);
        saasCreditDunning.setId(id);
        return saasCreditDunningDao.updateByPrimaryKey(saasCreditDunning) > 0;
    }

}


