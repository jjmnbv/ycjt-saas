package com.beitu.saas.rest.controller.credit.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 下午5:45
 * @description
 */
@ApiModel(value = "运营商H5返回参数")
public class CarrierH5Response implements ResponseData {

    @ApiModelProperty(value = "H5运营商认证URL地址")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CarrierH5Response(String url) {
        this.url = url;
    }

}
