package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditCarrierBaseService;
import com.beitu.saas.credit.dao.SaasCreditCarrierBaseDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierBaseVo;
import com.beitu.saas.credit.entity.SaasCreditCarrierBase;
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
 * Time: 18:11:44.641
 */
@Module(value = "运营商报告基本资料表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierBaseDaoImpl")
@Service
public class SaasCreditCarrierBaseServiceImpl extends AbstractBaseService implements SaasCreditCarrierBaseService {

    @Autowired
    private SaasCreditCarrierBaseDao saasCreditCarrierBaseDao;

    @Override
    public SaasCreditCarrierBase addSaasCreditCarrierBase(SaasCreditCarrierBaseVo saasCreditCarrierBaseVo) {
        return saasCreditCarrierBaseDao.insert(SaasCreditCarrierBaseVo.convertVOToEntity(saasCreditCarrierBaseVo));
    }

    @Override
    public SaasCreditCarrierBaseVo getByRecordId(Long recordId) {
        List<SaasCreditCarrierBase> saasCreditCarrierBaseList = saasCreditCarrierBaseDao.selectByParams(new HashMap<String, Object>(4) {{
            put("recordId", recordId);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditCarrierBaseList)) {
            return null;
        }
        return SaasCreditCarrierBaseVo.convertEntityToVO(saasCreditCarrierBaseList.get(0));
    }
}


