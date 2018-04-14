package com.beitu.saas.credit.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.credit.entity.SaasCreditCarrierRecord;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.654
 */

public interface SaasCreditCarrierRecordDao extends BaseMapper<SaasCreditCarrierRecord> {

    int batchAddSaasCreditCarrierRecord(List<SaasCreditCarrierRecord> saasCreditCarrierRecordList);

}