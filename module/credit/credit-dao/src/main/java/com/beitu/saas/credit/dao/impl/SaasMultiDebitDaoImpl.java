package com.beitu.saas.credit.dao.impl;
import com.beitu.saas.credit.dao.SaasMultiDebitDao;
import com.beitu.saas.credit.entity.SaasMultiDebitEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* User: fenqiguanjia
* Date: 2018-04-16
* Time: 21:04:02.120
*/

@Repository
public class SaasMultiDebitDaoImpl extends AbstractBaseMapper<SaasMultiDebitEntity> implements SaasMultiDebitDao {

    @Override
    public List<SaasMultiDebitEntity> selectMultiDebitEntityList(String mobile) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        return getSqlSession().selectList(this.getStatement("selectMultiDebitEntityList"), map);
    }
}