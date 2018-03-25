package com.beitu.saas.finance.dao.impl;

import com.beitu.saas.finance.dao.SaasCreditHistoryDao;
import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import com.fqgj.common.utils.DateUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:10.962
 */

@Repository
public class SaasCreditHistoryDaoImpl extends AbstractBaseMapper<SaasCreditHistoryEntity> implements SaasCreditHistoryDao {

    @Override
    public Long selectYesterdayCreditStatCredit(String merchantCode, Date yesterday) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        map.put("yesterday", DateUtil.getDate(yesterday,"yyyy-MM-dd"));
        return getSqlSession().selectOne(this.getStatement("selectYesterdayCreditStatCredit"), map);
    }
}