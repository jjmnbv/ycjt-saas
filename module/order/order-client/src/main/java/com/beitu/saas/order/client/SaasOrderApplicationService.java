package com.beitu.saas.order.client;

import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.entity.SaasOrderApplication;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.766
 */
public interface SaasOrderApplicationService<T extends BaseEntity> extends BaseService<T> {

    SaasOrderApplicationVo getByOrderNumb(String orderNumb);

    SaasOrderApplication create(SaasOrderApplicationVo saasOrderApplicationVo);

}