package com.beitu.saas.credit.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.credit.dao.SaasCreditBmpDetailDao;
import com.beitu.saas.credit.entity.SaasCreditBmpDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.609
 */

@Repository
public class SaasCreditBmpDetailDaoImpl extends AbstractBaseMapper<SaasCreditBmpDetail> implements SaasCreditBmpDetailDao {

    @Override
    public int batchAddSaasCreditBmpDetail(List<SaasCreditBmpDetail> saasCreditBmpDetailList) {
        return this.getSqlSession().insert(this.getStatement(".batchAddSaasCreditBmpDetail"), saasCreditBmpDetailList);
    }
}