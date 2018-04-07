package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditCarrierExtService;
import com.beitu.saas.credit.dao.SaasCreditCarrierExtDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierExtVo;
import com.beitu.saas.credit.entity.SaasCreditCarrierExt;
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
        return saasCreditCarrierExtDao.insert(SaasCreditCarrierExtVo.convertVOToEntity(saasCreditCarrierExtVo));
    }

    @Override
    public SaasCreditCarrierExtVo getByRecordId(Long recordId) {
        List<SaasCreditCarrierExt> saasCreditCarrierExtList = saasCreditCarrierExtDao.selectByParams(new HashMap<String, Object>(4) {{
            put("recordId", recordId);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditCarrierExtList)) {
            return null;
        }
        return SaasCreditCarrierExtVo.convertEntityToVO(saasCreditCarrierExtList.get(saasCreditCarrierExtList.size() - 1));
    }

}


