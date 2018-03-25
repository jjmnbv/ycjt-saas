package com.beitu.saas.order.client;

import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.entity.SaasOrder;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.744
 */
public interface SaasOrderService<T extends BaseEntity> extends BaseService<T> {

    List<SaasOrderVo> listByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode);

}