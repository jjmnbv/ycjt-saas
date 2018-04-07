package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditCarrierVo;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.627
 */
public interface SaasCreditCarrierService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditCarrier addSaasCreditCarrier(SaasCreditCarrierVo saasCreditCarrierVo);

    Boolean effectivenessCreditCarrier(String borrowerCode);

    SaasCreditCarrierVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode);

    Boolean updateSuccess(Long id);

}