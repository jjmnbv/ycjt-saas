package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditCarrierBaseVo;
import com.beitu.saas.credit.entity.SaasCreditCarrierBase;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.640
 */
public interface SaasCreditCarrierBaseService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditCarrierBase addSaasCreditCarrierBase(SaasCreditCarrierBaseVo saasCreditCarrierBaseVo);

    SaasCreditCarrierBaseVo getByRecordId(Long recordId);

}