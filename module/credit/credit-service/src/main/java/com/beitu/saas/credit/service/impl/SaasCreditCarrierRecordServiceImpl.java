package com.beitu.saas.credit.service.impl;
import com.beitu.saas.credit.client.SaasCreditCarrierRecordService;
import com.beitu.saas.credit.dao.SaasCreditCarrierRecordDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierRecordVo;
import com.beitu.saas.credit.enums.CreditCarrierRecordTypeEnum;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

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

    }

    @Override
    public List<SaasCreditCarrierRecordVo> listByRecordIdAndRecordTypeEnum(Long recordId, CreditCarrierRecordTypeEnum recordTypeEnum) {
        return null;
    }
}


