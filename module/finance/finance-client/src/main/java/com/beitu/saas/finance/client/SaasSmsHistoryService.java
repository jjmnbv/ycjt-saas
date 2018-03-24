package com.beitu.saas.finance.client;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.075
*/
public interface SaasSmsHistoryService<T extends BaseEntity> extends BaseService<T> {
    Long getYesterdaySmsStatCredit(String merchantCode, Date yesterday);
}