package com.beitu.saas.credit.dao;

import com.beitu.saas.credit.entity.SaasMultiDebitEntity;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-04-16
 * Time: 21:04:02.116
 */

public interface SaasMultiDebitDao extends BaseMapper<SaasMultiDebitEntity> {
    List<SaasMultiDebitEntity> selectMultiDebitEntityList(String mobile);
}