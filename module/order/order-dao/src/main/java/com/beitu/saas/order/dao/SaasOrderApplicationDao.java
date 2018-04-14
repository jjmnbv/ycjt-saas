package com.beitu.saas.order.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.order.entity.SaasOrderApplication;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.758
 */

public interface SaasOrderApplicationDao extends BaseMapper<SaasOrderApplication> {

    SaasOrderApplication selectByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

    int updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode);

    List<SaasOrderApplication> selectAllByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb);

}