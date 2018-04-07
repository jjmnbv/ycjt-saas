package com.beitu.saas.credit.client;

import com.beitu.saas.credit.domain.SaasCreditBmpDetailVo;
import com.beitu.saas.credit.entity.SaasCreditBmpDetail;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.620
 */
public interface SaasCreditBmpDetailService<T extends BaseEntity> extends BaseService<T> {

    SaasCreditBmpDetail addSaasCreditBmpDetail(SaasCreditBmpDetailVo saasCreditBmpDetailVo);

    List<SaasCreditBmpDetailVo> listByRecordId(Long recordId);

}