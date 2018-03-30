package com.beitu.saas.finance.client;
import com.beitu.saas.finance.client.param.CreditHistoryQueryParam;
import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:10.966
*/
public interface SaasCreditHistoryService<T extends BaseEntity> extends BaseService<T> {

    Long getYesterdayCreditStatCredit(String merchantCode, Date yesterday);

    List<SaasCreditHistoryEntity> getCreditListByParam(CreditHistoryQueryParam param, Page page);

    SaasCreditHistoryEntity addExpenditureCreditHistory(String merchantCode, Long credit, String opName, String comment);

    SaasCreditHistoryEntity addIncomeCreditHistory(String merchantCode, Long credit, String opName, String comment);
}