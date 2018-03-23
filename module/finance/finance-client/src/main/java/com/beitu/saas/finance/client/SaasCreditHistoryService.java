package com.beitu.saas.finance.client;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:10.966
*/
public interface SaasCreditHistoryService<T extends BaseEntity> extends BaseService<T> {

    Long getYesterdayCreditStatCredit(String merchantCode, Date yesterday);

}