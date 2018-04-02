package com.beitu.saas.rest.controller.h5.request;

import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/22 下午4:54
 * @description
 */
@ApiModel(description = "保存风控模块身份证信息")
public class CreditSaveIdentityInfoRequest extends ParamsObject {

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
        if (StringUtils.isNotEmpty(frontUrl)) {
            this.frontUrl = frontUrl;
        }
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        if (StringUtils.isNotEmpty(backUrl)) {
            this.backUrl = backUrl;
        }
    }

    public String getHoldUrl() {
        return holdUrl;
    }

    public void setHoldUrl(String holdUrl) {
        if (StringUtils.isNotEmpty(holdUrl)) {
            this.holdUrl = holdUrl;
        }
    }

    @Override
    public void validate() {

    }

}
