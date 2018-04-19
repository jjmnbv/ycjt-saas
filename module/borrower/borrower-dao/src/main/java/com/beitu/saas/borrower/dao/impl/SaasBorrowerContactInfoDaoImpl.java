package com.beitu.saas.borrower.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.borrower.dao.SaasBorrowerContactInfoDao;
import com.beitu.saas.borrower.entity.SaasBorrowerContactInfo;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 16:43:06.598
 */

@Repository
public class SaasBorrowerContactInfoDaoImpl extends AbstractBaseMapper<SaasBorrowerContactInfo> implements SaasBorrowerContactInfoDao {
    
    @Override
    public SaasBorrowerContactInfo getByBorrowerCode(String merchantCode, String borrowerCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("merchantCode", merchantCode);
        params.put("borrowerCode", borrowerCode);
        List<SaasBorrowerContactInfo> list = this.getSqlSession().selectList(this.getStatement(".selectByBorrowerCodeAndMerchantCode"), params);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public List<SaasBorrowerContactInfo> getListByBorrowerCode(String merchantCode, String borrowerCode) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("merchantCode", merchantCode);
        params.put("borrowerCode", borrowerCode);
        return this.getSqlSession().selectList(this.getStatement(".selectByBorrowerCodeAndMerchantCode"), params);
    }
    
}