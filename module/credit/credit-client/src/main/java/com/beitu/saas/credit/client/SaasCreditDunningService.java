package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditDunningVo;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.662
 */
public interface SaasCreditDunningService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditDunning addSaasCreditDunning(SaasCreditDunningVo saasCreditDunningVo);

    Boolean effectivenessCreditDunning(String borrowerCode);

    SaasCreditDunningVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode);

    Boolean updateSuccess(Long id);

}