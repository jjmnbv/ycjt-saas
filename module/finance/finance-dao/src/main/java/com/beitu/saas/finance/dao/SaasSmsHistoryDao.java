package com.beitu.saas.finance.dao;

import com.beitu.saas.finance.entity.SaasSmsHistoryEntity;
import com.fqgj.common.base.BaseMapper;

import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.073
*/

public interface SaasSmsHistoryDao  extends BaseMapper<SaasSmsHistoryEntity> {
    Long selectYesterdaySmsStatCredit(String merchantCode, Date yesterday);

}