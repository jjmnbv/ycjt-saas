package com.beitu.saas.rest.controller.collection.response;

import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

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
    private List<CollectionOrderInfoDetailVo> collectionOrderInfoDetailVos=new ArrayList<>();

    public CollectionOrderListResponse(List<CollectionOrderInfoDetailVo> collectionOrderInfoDetailVos) {
        this.collectionOrderInfoDetailVos = collectionOrderInfoDetailVos;
    }

    public List<CollectionOrderInfoDetailVo> getCollectionOrderInfoDetailVos() {
        return collectionOrderInfoDetailVos;
    }

    public CollectionOrderListResponse setCollectionOrderInfoDetailVos(List<CollectionOrderInfoDetailVo> collectionOrderInfoDetailVos) {
        this.collectionOrderInfoDetailVos = collectionOrderInfoDetailVos;
        return this;
    }
}
