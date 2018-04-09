package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditCarrierRecordService;
import com.beitu.saas.credit.dao.SaasCreditCarrierRecordDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierRecordVo;
import com.beitu.saas.credit.entity.SaasCreditCarrierRecord;
import com.beitu.saas.credit.enums.CreditCarrierRecordTypeEnum;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.658
 */
@Module(value = "运营商报告通话详情表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierRecordDaoImpl")
@Service
public class SaasCreditCarrierRecordServiceImpl extends AbstractBaseService implements SaasCreditCarrierRecordService {

    @Autowired
    private SaasCreditCarrierRecordDao saasCreditCarrierRecordDao;

    @Override
    public void batchAddSaasCreditCarrierRecord(List list) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<SaasCreditCarrierRecord> addList = new ArrayList<>(list.size());
            list.forEach(object -> addList.add(SaasCreditCarrierRecordVo.convertVOToEntity((SaasCreditCarrierRecordVo) object)));
            saasCreditCarrierRecordDao.batchAddSaasCreditCarrierRecord(addList);
        }
    }

    @Override
    public List<SaasCreditCarrierRecordVo> listByRecordIdAndRecordTypeEnum(Long recordId, CreditCarrierRecordTypeEnum recordTypeEnum) {
        List<SaasCreditCarrierRecord> saasCreditCarrierRecordList = saasCreditCarrierRecordDao.selectByParams(new HashMap<String, Object>(4) {{
            put("recordId", recordId);
            if (recordTypeEnum != null) {
                put("type", recordTypeEnum.getType());
            }
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditCarrierRecordList)) {
            return null;
        }
        List<SaasCreditCarrierRecordVo> results = new ArrayList<>(saasCreditCarrierRecordList.size());
        saasCreditCarrierRecordList.forEach(saasCreditCarrierRecord -> results.add(SaasCreditCarrierRecordVo.convertEntityToVO(saasCreditCarrierRecord)));
        return results;
    }
}


