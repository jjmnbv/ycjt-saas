package com.beitu.saas.credit.dao.impl;
import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.credit.dao.SaasCreditCarrierRecordDao;
import com.beitu.saas.credit.entity.SaasCreditCarrierRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.654
*/

@Repository
public class SaasCreditCarrierRecordDaoImpl extends AbstractBaseMapper<SaasCreditCarrierRecord> implements SaasCreditCarrierRecordDao {

    @Override
    public int batchAddSaasCreditCarrierRecord(List<SaasCreditCarrierRecord> saasCreditCarrierRecordList) {
        return this.getSqlSession().insert(this.getStatement(".batchAddSaasCreditCarrierRecord"), saasCreditCarrierRecordList);
    }

}