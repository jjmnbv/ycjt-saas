package com.beitu.saas.auth.dao.impl;

import com.beitu.saas.auth.dao.SaasMerchantConfigDao;
import com.beitu.saas.auth.entity.SaasMerchantConfig;
import com.fqgj.common.base.AbstractBaseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.667
*/

@Repository
public class SaasMerchantConfigDaoImpl extends AbstractBaseMapper<SaasMerchantConfig> implements SaasMerchantConfigDao {

    @Override
    public Integer updateByMerchantCode(SaasMerchantConfig record) {
        record.setGmtModified(new Date());
        record.setGmtCreate((Date)null);
        return Integer.valueOf(this.getSqlSession().update(this.getStatement(".updateByMerchantCode"), record));
    }
}