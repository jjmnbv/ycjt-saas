package com.beitu.saas.finance.dao;

import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
import com.fqgj.common.base.BaseMapper;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.369
*/

public interface SaasConsumeDayStatDao extends BaseMapper<SaasConsumeDayStatEntity> {
    SaasConsumeDayStatEntity getLastClearConsumeDayStat(String merchantCode);
}