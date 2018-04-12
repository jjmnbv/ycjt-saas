package com.beitu.saas.rest.controller.credit.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/4/10 下午10:48
 * @description
 */
@ApiModel(description = "H5得到运营商认证链接请求参数")
public class H5GetCarrierUrlRequest extends ParamsObject {

    @ApiModelProperty(value = "浏览器类型(2为微信)")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public void validate() {

    }

}
