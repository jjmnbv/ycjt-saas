package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditBmpVo;
import com.beitu.saas.credit.entity.SaasCreditBmp;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.601
 */
public interface SaasCreditBmpService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditBmp addSaasCreditBmp(SaasCreditBmpVo saasCreditBmpVo);

    SaasCreditBmpVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode);

    Boolean updateSuccess(Long id);

}