package com.beitu.saas.order.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.873
 */

public interface SaasOrderStatusHistoryDao extends BaseMapper<SaasOrderStatusHistory> {

    SaasOrderStatusHistory selectOrderStatusHistoryByOrderNumb(String orderNumb);

    List<SaasOrderStatusHistory> selectByCurrentOrderStatusAndOrderNumb(Integer currentOrderStatus, String orderNumb);

}