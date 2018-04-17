package com.beitu.saas.credit.client;

import com.beitu.saas.credit.entity.SaasMultiDebitEntity;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-04-16
 * Time: 21:04:02.123
 */
public interface SaasMultiDebitService<T extends BaseEntity> extends BaseService<T> {
    List<SaasMultiDebitEntity> getMultiDebitEntityList(String mobile);

    void updateExpiredMultiDebitEntity();
}