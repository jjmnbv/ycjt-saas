package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditCarrierRecordVo;
import com.beitu.saas.credit.enums.CreditCarrierRecordTypeEnum;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.657
 */
public interface SaasCreditCarrierRecordService<T extends BaseEntity> extends BaseService<T> {

    void batchAddSaasCreditCarrierRecord(List<SaasCreditCarrierRecordVo> saasCreditCarrierRecordVoList);

    List<SaasCreditCarrierRecordVo> listByRecordIdAndRecordTypeEnum(Long recordId, CreditCarrierRecordTypeEnum recordTypeEnum);

}