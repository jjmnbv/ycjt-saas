package com.beitu.saas.borrower.dao.impl;

import com.beitu.saas.borrower.entity.SaasBorrowerGpsLog;
import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.borrower.dao.SaasBorrowerGpsLogDao;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 14:04:03.483
 */

@Repository
public class SaasBorrowerGpsLogDaoImpl extends AbstractBaseMapper<SaasBorrowerGpsLog> implements SaasBorrowerGpsLogDao {
    
    @Override
    public Integer batchAddSaasBorrowerGpsLog(List<SaasBorrowerGpsLog> saasBorrowerGpsLogList) {
        if (CollectionUtils.isEmpty(saasBorrowerGpsLogList)) {
            return 0;
        }
        return this.getSqlSession().insert(this.getStatement(".batchAddSaasBorrowerGpsLog"), saasBorrowerGpsLogList);
    }
    
    @Override
    public List<SaasBorrowerGpsLog> selectByBorrowerCodeAndMerchantCode(String merchantCode, String borrowerCode, Integer limit) {
        Map<String, Object> params = new HashMap<>(6);
        params.put("merchantCode", merchantCode);
        params.put("borrowerCode", borrowerCode);
        params.put("limit", limit);
        return this.getSqlSession().selectOne(this.getStatement(".selectByBorrowerCodeAndMerchantCode"), params);
    }
    
}