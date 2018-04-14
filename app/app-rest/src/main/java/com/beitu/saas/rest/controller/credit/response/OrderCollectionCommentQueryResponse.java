package com.beitu.saas.rest.controller.credit.response;

import com.beitu.saas.app.application.collection.vo.CollectionCommentListVo;
import com.beitu.saas.app.application.order.vo.OrderApplicationListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:12
 * @description
 */
@ApiModel(value = "催收列表")
public class OrderCollectionCommentQueryResponse implements ResponseData {

    @ApiModelProperty(value = "催收列表信息")
    private List<CollectionCommentListVo> collectionCommentListVoList;

    public OrderCollectionCommentQueryResponse(List<CollectionCommentListVo> collectionCommentListVoList) {
        this.collectionCommentListVoList = collectionCommentListVoList;
    }

    public List<CollectionCommentListVo> getCollectionCommentListVoList() {
        return collectionCommentListVoList;
    }

    public void setCollectionCommentListVoList(List<CollectionCommentListVo> collectionCommentListVoList) {
        this.collectionCommentListVoList = collectionCommentListVoList;
    }
}