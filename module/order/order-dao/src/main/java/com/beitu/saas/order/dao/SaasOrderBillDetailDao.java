package com.beitu.saas.order.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.order.entity.SaasOrderBillDetail;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.855
 */

public interface SaasOrderBillDetailDao extends BaseMapper<SaasOrderBillDetail> {

    List<SaasOrderBillDetail> selectByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode);

    List<SaasOrderBillDetail> selectByOrderNumb(String orderNumb);

}