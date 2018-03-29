package com.beitu.saas.finance.dao.impl;

import com.beitu.saas.finance.dao.SaasSmsHistoryDao;
import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.beitu.saas.finance.entity.SaasSmsHistoryEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import com.fqgj.common.utils.DateUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:11.074
 */

@Repository
public class SaasSmsHistoryDaoImpl extends AbstractBaseMapper<SaasSmsHistoryEntity> implements SaasSmsHistoryDao {

    @Override
    public Long selectYesterdaySmsStatCredit(String merchantCode, Date yesterday) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        map.put("yesterday", DateUtil.getDate(yesterday, "yyyy-MM-dd"));
        return getSqlSession().selectOne(this.getStatement("selectYesterdaySmsStatCredit"), map);
    }

    @Override
    public List<SaasSmsHistoryEntity> selectSmsListByParam(Map map){
        return getSqlSession().selectList(this.getStatement("selectSmsListByParam"), map);
    }

    @Override
    public Integer queryTotalSmsListByParam(Map map){
        return getSqlSession().selectOne(this.getStatement("queryTotalSmsListByParam"), map);
    }
}