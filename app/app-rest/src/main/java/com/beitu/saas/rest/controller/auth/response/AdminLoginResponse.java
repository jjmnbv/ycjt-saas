package com.beitu.saas.rest.controller.auth.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/29 下午9:01
 * @description
 */
@ApiModel
public class AdminLoginResponse implements ResponseData {

    @ApiModelProperty
    private String token;

    @ApiModelProperty("用户名称")
    private String adminName;

    @ApiModelProperty("用户头像")
    private String adminIconurl;

    @ApiModelProperty("机构名称")
    private String merchantName;

    @ApiModelProperty("机构头像")
    private String merchantIconUrl;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminIconurl() {
        return adminIconurl;
    }

    public void setAdminIconurl(String adminIconurl) {
        this.adminIconurl = adminIconurl;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantIconUrl() {
        return merchantIconUrl;
    }

    public void setMerchantIconUrl(String merchantIconUrl) {
        this.merchantIconUrl = merchantIconUrl;
    }
}
