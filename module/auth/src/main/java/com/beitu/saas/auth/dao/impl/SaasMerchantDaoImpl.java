package com.beitu.saas.auth.dao.impl;

import com.beitu.saas.auth.dao.SaasMerchantDao;
import com.beitu.saas.auth.entity.SaasMerchant;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: xiaochong
 * Date: 2018-03-22
 * Time: 15:36:13.655
 */

@Repository
public class SaasMerchantDaoImpl extends AbstractBaseMapper<SaasMerchant> implements SaasMerchantDao {

    @Override
    public List<String> selectAllMerchantCode() {
        return getSqlSession().selectList(this.getStatement("selectAllMerchantCode"));
    }
}