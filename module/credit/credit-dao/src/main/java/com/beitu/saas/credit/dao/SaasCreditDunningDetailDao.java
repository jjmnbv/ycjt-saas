package com.beitu.saas.credit.dao;

import com.beitu.saas.credit.entity.SaasCreditDunningDetail;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.664
 */

public interface SaasCreditDunningDetailDao extends BaseMapper<SaasCreditDunningDetail> {

    int batchAddSaasCreditDunningDetail(List<SaasCreditDunningDetail> saasCreditDunningDetailList);

}