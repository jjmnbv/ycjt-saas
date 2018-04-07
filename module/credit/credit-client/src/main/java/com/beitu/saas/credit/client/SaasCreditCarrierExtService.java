package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditCarrierExtVo;
import com.beitu.saas.credit.entity.SaasCreditCarrierExt;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.652
 */
public interface SaasCreditCarrierExtService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditCarrierExt addSaasCreditCarrierExt(SaasCreditCarrierExtVo saasCreditCarrierExtVo);

    SaasCreditCarrierExtVo getByRecordId(Long recordId);

    Boolean updateSuccess(Long id);

}