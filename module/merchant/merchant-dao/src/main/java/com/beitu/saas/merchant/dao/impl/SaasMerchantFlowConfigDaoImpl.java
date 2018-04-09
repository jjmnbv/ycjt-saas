package com.beitu.saas.merchant.dao.impl;

import com.beitu.saas.merchant.dao.SaasMerchantFlowConfigDao;
import com.beitu.saas.merchant.entity.SaasMerchantFlowConfig;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
* User: xiaochong
* Date: 2018-04-09
* Time: 11:49:34.711
*/

@Repository
public class SaasMerchantFlowConfigDaoImpl extends AbstractBaseMapper<SaasMerchantFlowConfig> implements SaasMerchantFlowConfigDao {

    @Override
    public SaasMerchantFlowConfig replace(SaasMerchantFlowConfig record) {
        record.setGmtModified(new Date());
        this.getSqlSession().insert(this.getStatement(".replace"), record);
        return record;
    }
}