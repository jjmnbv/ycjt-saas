package com.beitu.saas.rest.controller.collection.request;

import io.swagger.annotations.ApiModel;

/**
 * @author linanjun
 * @create 2018/4/1 下午8:46
 * @description
 */
@ApiModel(value = "新增催收记录")
public class AddCollectionOrderNoteRequest {

    private String name;

    private String mobile;

    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
