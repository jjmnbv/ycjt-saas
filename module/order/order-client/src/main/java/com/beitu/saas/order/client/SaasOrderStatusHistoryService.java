package com.beitu.saas.order.client;

import com.beitu.saas.order.domain.SaasOrderStatusHistoryVo;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.878
 */
public interface SaasOrderStatusHistoryService<T extends BaseEntity> extends BaseService<T> {

    SaasOrderStatusHistoryVo getLatestOrderStatusHistoryByOrderNumb(String orderNumb);

    List<SaasOrderStatusHistoryVo> listOrderStatusHistoryByOrderNumb(String orderNumb);

    String getLoanLendRemark(String orderNumb);

}