package com.beitu.saas.finance.client;
import com.beitu.saas.finance.client.param.SmsHistoryQueryParam;
import com.beitu.saas.finance.entity.SaasSmsHistoryEntity;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.075
*/
public interface SaasSmsHistoryService<T extends BaseEntity> extends BaseService<T> {
    Long getYesterdaySmsStatCredit(String merchantCode, Date yesterday);

    List<SaasSmsHistoryEntity> getSmsListByParam(SmsHistoryQueryParam param, Page page);
}