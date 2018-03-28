package com.beitu.saas.order.dao.impl;

import com.beitu.saas.order.dao.SaasOrderBillDetailDao;
import com.beitu.saas.order.entity.SaasOrderBillDetail;
import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.856
 */

@Repository
public class SaasOrderBillDetailDaoImpl extends AbstractBaseMapper<SaasOrderBillDetail> implements SaasOrderBillDetailDao {

    @Override
    public List<SaasOrderBillDetail> selectByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("borrowerCode", borrowerCode);
        params.put("merchantCode", merchantCode);
        return this.getSqlSession().selectList(this.getStatement(".selectByBorrowerCodeAndMerchantCode"), params);
    }

    @Override
    public List<SaasOrderBillDetail> selectByOrderNumb(String orderNumb) {
        return this.getSqlSession().selectList(this.getStatement(".selectByOrderNumb"), orderNumb);
    }


    @Override
    public LoanDataDetailVo selectLoanDataDetail(String merchantCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("merchantCode", merchantCode);
        return this.getSqlSession().selectOne(this.getStatement(".selectLoanDataDetail"), params);    }
}