package com.beitu.saas.credit.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.credit.dao.SaasCreditDunningDetailDao;
import com.beitu.saas.credit.entity.SaasCreditDunningDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.665
 */

@Repository
public class SaasCreditDunningDetailDaoImpl extends AbstractBaseMapper<SaasCreditDunningDetail> implements SaasCreditDunningDetailDao {

    @Override
    public int batchAddSaasCreditDunningDetail(List<SaasCreditDunningDetail> saasCreditDunningDetailList) {
        return this.getSqlSession().insert(this.getStatement(".batchAddSaasCreditDunningDetail"), saasCreditDunningDetailList);
    }

}