package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午8:17
 * @description
 */
@ApiModel(value = "用户身份信息模块")
public class BorrowerIdentityInfoVo implements ResponseData {

    /**
     * 身份证正面面图片URL
     */
    @ApiModelProperty(value = "身份证正面面图片URL")
    private String frontUrl;
    /**
     * 身份证反面图片URL
     */
    @ApiModelProperty(value = "身份证反面图片URL")
    private String backUrl;
    /**
     * 手持身份证图片URL
     */
    @ApiModelProperty(value = "手持身份证图片URL")
    private String holdUrl;

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getHoldUrl() {
        return holdUrl;
    }

    public void setHoldUrl(String holdUrl) {
        this.holdUrl = holdUrl;
    }

}
