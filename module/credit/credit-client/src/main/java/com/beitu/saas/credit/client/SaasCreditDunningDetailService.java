package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditDunningDetailVo;
import com.beitu.saas.credit.enums.CreditDunningDetailTypeEnum;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.668
 */
public interface SaasCreditDunningDetailService<T extends BaseEntity> extends BaseService<T> {

    void batchAddSaasCreditDunningDetail(List<SaasCreditDunningDetailVo> saasCreditDunningDetailVoList);

    List<SaasCreditDunningDetailVo> listByRecordId(Long recordId, CreditDunningDetailTypeEnum dunningDetailTypeEnum);

}