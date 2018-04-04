package com.beitu.saas.app.application.collection.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/4/4 下午2:30
 * @description
 */
@ApiModel(value = "催收列表信息")
public class CollectionCommentListVo implements ResponseData {

    @ApiModelProperty(value = "催收时间")
    private String collectionDate;

    @ApiModelProperty(value = "联系人")
    private String collectionName;

    @ApiModelProperty(value = "联系电话")
    private String collectionMobile;

    @ApiModelProperty(value = "备注")
    private String remark;

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionMobile() {
        return collectionMobile;
    }

    public void setCollectionMobile(String collectionMobile) {
        this.collectionMobile = collectionMobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
