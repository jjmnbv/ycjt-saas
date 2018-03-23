package com.beitu.saas.channel.client;

import com.beitu.saas.channel.domain.CollectionCommentVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.352
 */
public interface SaasCollectionCommentService<T extends BaseEntity> extends BaseService<T> {
    /**
     * 新增催记
     *
     * @param commentVo
     */
    void createCollectionComment(CollectionCommentVo commentVo);
}