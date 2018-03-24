package com.beitu.saas.rest.controller.auth.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/23 下午2:20
 * @description
 */
public class OperationButtonResponse {

    @ApiModelProperty("按钮名字")
    private String name;

    @ApiModelProperty("按钮id")
    private Long buttonId;

    @ApiModelProperty("父按钮id")
    private Integer pId;

    @ApiModelProperty("是否为父按钮id")
    private Boolean isParent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }
}
