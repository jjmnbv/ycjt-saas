package com.beitu.saas.order.dao;

import com.beitu.saas.order.entity.SaasOrderLendRemark;
import com.fqgj.common.base.BaseMapper;

/**
 * User: jungle
 * Date: 2018-04-12
 * Time: 20:39:32.554
 */

public interface SaasOrderLendRemarkDao extends BaseMapper<SaasOrderLendRemark> {

    String selectLendWayByOrderNumb(String orderNumb);

    String selectLendCertificateByOrderNumb(String orderNumb);

}