package com.beitu.saas.finance.dao;

import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.fqgj.common.base.BaseMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:10.957
 */

public interface SaasCreditHistoryDao extends BaseMapper<SaasCreditHistoryEntity> {
    Long selectYesterdayCreditStatCredit(String merchantCode, Date yesterday);

    List<SaasCreditHistoryEntity> selectCreditListByParam(Map map);

    Integer queryTotalCreditListByParam(Map map);
}