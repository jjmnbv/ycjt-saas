package com.beitu.saas.collection.client;

import com.beitu.saas.collection.param.CollectionCommentParam;
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
     * @param param
     */
    void createCollectionComment(CollectionCommentParam param, String followCode, String followUp);
}