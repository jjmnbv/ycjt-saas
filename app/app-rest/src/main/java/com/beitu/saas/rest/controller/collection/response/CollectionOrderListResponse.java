package com.beitu.saas.rest.controller.collection.response;

import com.beitu.saas.app.application.collection.vo.CollectionOrderListVo;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/2/1
 * Time: 上午10:08
 */
@ApiModel(value = "催收列表")
public class CollectionOrderListResponse implements ResponseData {

    @ApiModelProperty(value = "催收列表信息")
    private List<CollectionOrderListVo> collectionOrderListVoList;

    public CollectionOrderListResponse(List<CollectionOrderListVo> collectionOrderListVoList) {
        this.collectionOrderListVoList = collectionOrderListVoList;
    }

    public List<CollectionOrderListVo> getCollectionOrderListVoList() {
        return collectionOrderListVoList;
    }

    public void setCollectionOrderListVoList(List<CollectionOrderListVo> collectionOrderListVoList) {
        this.collectionOrderListVoList = collectionOrderListVoList;
    }
}
