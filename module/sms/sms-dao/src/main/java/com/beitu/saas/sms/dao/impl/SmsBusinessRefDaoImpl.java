package com.beitu.saas.sms.dao.impl;

import com.beitu.saas.sms.entity.SmsBusinessRef;
import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.sms.dao.SmsBusinessRefDao;
import com.beitu.saas.sms.model.BusinessRefInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-02-27
 * Time: 17:09:17.440
 */

@Repository
public class SmsBusinessRefDaoImpl extends AbstractBaseMapper<SmsBusinessRef> implements SmsBusinessRefDao {

    @Override
    public List<BusinessRefInfo> getAll() {
        return this.getSqlSession().selectList(this.getStatement(".getAllBusinessRefInfo"));
    }

}