package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditTongdunVo;
import com.beitu.saas.credit.entity.SaasCreditTongdun;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.672
 */
public interface SaasCreditTongdunService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditTongdun addSaasCreditTongdun(SaasCreditTongdunVo saasCreditTongdunVo);

    Boolean effectivenessCreditTongdun(String borrowerCode);

    SaasCreditTongdunVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode);

    SaasCreditTongdunVo getEffectivenessByMobileAndIdentityCode(String mobile, String identityCode);

    Boolean updateSuccess(Long id);

}