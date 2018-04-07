package com.beitu.saas.credit.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.credit.dao.SaasCreditCarrierBillDao;
import com.beitu.saas.credit.entity.SaasCreditCarrierBill;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.643
 */

@Repository
public class SaasCreditCarrierBillDaoImpl extends AbstractBaseMapper<SaasCreditCarrierBill> implements SaasCreditCarrierBillDao {

    @Override
    public int batchAddSaasCreditCarrierBill(List<SaasCreditCarrierBill> saasCreditCarrierBillList) {
        return this.getSqlSession().insert(this.getStatement(".batchAddSaasCreditCarrierBill"), saasCreditCarrierBillList);
    }

}