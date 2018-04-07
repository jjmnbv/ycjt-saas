package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditCarrierBillVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.647
 */
public interface SaasCreditCarrierBillService<T extends BaseEntity> extends BaseService<T> {

    void batchAddSaasCreditCarrierBill(List<SaasCreditCarrierBillVo> saasCreditCarrierBillVoList);

    List<SaasCreditCarrierBillVo> listByRecordId(Long recordId);

}