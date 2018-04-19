package com.beitu.saas.order.dao.impl;

import com.beitu.saas.order.dao.SaasOrderBillDetailDao;
import com.beitu.saas.order.entity.SaasOrderBillDetail;
import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.LoanStateDetailVo;
import com.beitu.saas.order.vo.DashboardOrderVo;
import com.fqgj.common.api.Page;
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
    public List<SaasOrderBillDetail> selectByOrderNumbAndMerchantCode(String orderNumb, String merchantCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("merchantCode", merchantCode);
        return this.getSqlSession().selectList(this.getStatement(".selectByOrderNumbAndMerchantCode"), params);
    }

    @Override
    public int countByConditions(Map<String, Object> conditions) {
        return this.getSqlSession().selectOne(this.getStatement(".countByConditions"), conditions);
    }

    @Override
    public List<SaasOrderBillDetail> selectByConditions(Map<String, Object> conditions) {
        return this.getSqlSession().selectList(this.getStatement(".selectByConditions"), conditions);
    }


    @Override
    public LoanDataDetailVo selectLoanDataDetail(String merchantCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("merchantCode", merchantCode);
        return this.getSqlSession().selectOne(this.getStatement(".selectLoanDataDetail"), params);
    }

    @Override
    public List<LoanStateDetailVo> selectLoanStatDetailList(String merchantCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("merchantCode", merchantCode);
        return this.getSqlSession().selectList(this.getStatement(".selectLoanStatDetailList"), params);
    }

    @Override
    public List<LoanStateDetailVo> selectDestroyStatDetailList(String merchantCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("merchantCode", merchantCode);
        return this.getSqlSession().selectList(this.getStatement(".selectDestroyStatDetailList"), params);    }

    @Override
    public List<DashboardOrderVo> selectNoRepayOrder(String merchantCode, Page page) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("merchantCode", merchantCode);
        if (page != null) {
            page.setTotalCount(this.countNoRepayOrder(merchantCode));
            map.put("page", page);
        }
        return this.getSqlSession().selectList(this.getStatement(".selectNoRepayOrder"), map);
    }

    @Override
    public Integer countNoRepayOrder(String merchantCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        return getSqlSession().selectOne(this.getStatement("countNoRepayOrder"), map);
    }

    @Override
    public List<DashboardOrderVo> selectOverdueOrder(String merchantCode, Page page) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("merchantCode", merchantCode);
        if (page != null) {
            page.setTotalCount(this.countOverdueOrder(merchantCode));
            map.put("page", page);
        }
        return this.getSqlSession().selectList(this.getStatement(".selectOverdueOrder"), map);
    }

    @Override
    public Integer countOverdueOrder(String merchantCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        return getSqlSession().selectOne(this.getStatement("countOverdueOrder"), map);
    }

}