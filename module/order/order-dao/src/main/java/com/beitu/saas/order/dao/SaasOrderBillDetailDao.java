package com.beitu.saas.order.dao;

import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.LoanStateDetailVo;
import com.beitu.saas.order.vo.NoRepayOrderVo;
import com.beitu.saas.order.vo.OverdueOrderVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.order.entity.SaasOrderBillDetail;

import java.util.List;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.855
 */

public interface SaasOrderBillDetailDao extends BaseMapper<SaasOrderBillDetail> {

    List<SaasOrderBillDetail> selectByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode);

    List<SaasOrderBillDetail> selectByOrderNumbAndMerchantCode(String orderNumb, String merchantCode);

    int countByConditions(Map<String, Object> conditions);

    List<SaasOrderBillDetail> selectByConditions(Map<String, Object> conditions);

    LoanDataDetailVo selectLoanDataDetail(String merchantCode);

    List<LoanStateDetailVo> selectLoanStatDetailList(String merchantCode);

    List<NoRepayOrderVo> selectNoRepayOrder(String merchantCode, Page page);

    Integer countNoRepayOrder(String merchantCode);

    List<OverdueOrderVo> selectOverdueOrder(String merchantCode, Page page);

    Integer countOverdueOrder(String merchantCode);

}