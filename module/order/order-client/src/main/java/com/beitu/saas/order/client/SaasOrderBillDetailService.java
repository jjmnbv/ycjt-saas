package com.beitu.saas.order.client;

import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.NoRepayOrderVo;
import com.beitu.saas.order.vo.OverdueOrderVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.868
 */
public interface SaasOrderBillDetailService<T extends BaseEntity> extends BaseService<T> {

    List<SaasOrderBillDetailVo> listByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode);

    List<SaasOrderBillDetailVo> listByOrderNumb(String orderNumb);

    LoanDataDetailVo getLoanDataDetailVo(String merchantCode);

    List<NoRepayOrderVo> getNoRepayOrderListByPage(String merchantCode, Page page);

    List<OverdueOrderVo> getOverdueOrderListByPage(String merchantCode, Page page);
}