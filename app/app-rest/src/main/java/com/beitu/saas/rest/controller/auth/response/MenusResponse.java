package com.beitu.saas.rest.controller.auth.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/23 上午11:38
 * @description
 */
@ApiModel("菜单")
public class MenusResponse {

    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty("菜单链接")
    private String link;

    @ApiModelProperty("父菜单id")
    private Integer pId;

    @ApiModelProperty("是否为父菜单")
    private Boolean isParent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
