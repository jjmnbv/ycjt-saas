package com.beitu.saas.order.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.order.entity.SaasOrder;

import java.util.List;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.630
 */

public interface SaasOrderDao extends BaseMapper<SaasOrder> {

    int countByConditions(Map<String, Object> conditions);

    List<SaasOrder> selectByConditions(Map<String, Object> conditions);

}