package com.beitu.saas.credit.service.impl;

import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.dao.SaasCreditCarrierDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierVo;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.628
 */
@Module(value = "运营商报告查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierDaoImpl")
@Service
public class SaasCreditCarrierServiceImpl extends AbstractBaseService implements SaasCreditCarrierService {

    @Autowired
    private SaasCreditCarrierDao saasCreditCarrierDao;

    @Override
    public SaasCreditCarrier addSaasCreditCarrier(SaasCreditCarrierVo saasCreditCarrierVo) {
        return saasCreditCarrierDao.insert(SaasCreditCarrierVo.convertVOToEntity(saasCreditCarrierVo));
    }

    @Override
    public Boolean effectivenessCreditCarrier(String borrowerCode) {
        List<SaasCreditCarrier> saasCreditCarrierList = saasCreditCarrierDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditCarrierList)) {
            return Boolean.FALSE;
        }
        SaasCreditCarrier saasCreditCarrier = saasCreditCarrierList.get(saasCreditCarrierList.size() - 1);
        if (DateUtil.isExceedOneMonth(saasCreditCarrier.getGmtCreate())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public SaasCreditCarrierVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        List<SaasCreditCarrier> saasCreditCarrierList = saasCreditCarrierDao.selectByParams(new HashMap<String, Object>(4) {{
            put("merchantCode", merchantCode);
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditCarrierList)) {
            return null;
        }
        return SaasCreditCarrierVo.convertEntityToVO(saasCreditCarrierList.get(saasCreditCarrierList.size() - 1));
    }

    @Override
    public Boolean updateSuccess(Long id) {
        SaasCreditCarrier saasCreditCarrier = new SaasCreditCarrier();
        saasCreditCarrier.setSuccess(Boolean.TRUE);
        saasCreditCarrier.setId(id);
        return saasCreditCarrierDao.updateByPrimaryKey(saasCreditCarrier) > 0;
    }
}


