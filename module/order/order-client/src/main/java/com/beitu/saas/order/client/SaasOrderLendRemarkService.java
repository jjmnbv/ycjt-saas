package com.beitu.saas.order.client;

import com.beitu.saas.order.domain.SaasOrderLendRemarkVo;
import com.beitu.saas.order.entity.SaasOrderLendRemark;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-12
 * Time: 20:39:32.648
 */
public interface SaasOrderLendRemarkService<T extends BaseEntity> extends BaseService<T> {

    SaasOrderLendRemark save(SaasOrderLendRemarkVo saasOrderLendRemarkVo);

    String getLendWayByOrderNumb(String orderNumb);

    String getLendCertificateByOrderNumb(String orderNumb);

}