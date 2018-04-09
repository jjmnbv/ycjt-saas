package com.beitu.saas.credit.dao;

import com.beitu.saas.credit.entity.SaasCreditBmpDetail;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.609
 */

public interface SaasCreditBmpDetailDao extends BaseMapper<SaasCreditBmpDetail> {

    int batchAddSaasCreditBmpDetail(List<SaasCreditBmpDetail> saasCreditBmpDetailList);

}