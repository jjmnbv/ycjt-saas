package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditTongdunDetailVo;
import com.beitu.saas.credit.entity.SaasCreditTongdunDetail;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.678
 */
public interface SaasCreditTongdunDetailService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditTongdunDetail addSaasCreditTongdunDetail(SaasCreditTongdunDetailVo saasCreditTongdunDetailVo);

    SaasCreditTongdunDetailVo getByRecordId(Long recordId);

}