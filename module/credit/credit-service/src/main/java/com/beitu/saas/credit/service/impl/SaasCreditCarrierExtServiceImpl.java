package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditCarrierExtService;
import com.beitu.saas.credit.dao.SaasCreditCarrierExtDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierExtVo;
import com.beitu.saas.credit.entity.SaasCreditCarrierExt;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.653
*/
@Module(value = "运营商报告扩充资料表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierExtDaoImpl")
@Service
public class SaasCreditCarrierExtServiceImpl extends AbstractBaseService implements SaasCreditCarrierExtService {

    @Autowired
    private SaasCreditCarrierExtDao saasCreditCarrierExtDao;

    @Override
    public SaasCreditCarrierExt addSaasCreditCarrierExt(SaasCreditCarrierExtVo saasCreditCarrierExtVo) {
        return null;
    }

    @Override
    public SaasCreditCarrierExtVo getByRecordId(Long recordId) {
        return null;
    }

    @Override
    public Boolean updateSuccess(Long id) {
        return null;
    }
}


