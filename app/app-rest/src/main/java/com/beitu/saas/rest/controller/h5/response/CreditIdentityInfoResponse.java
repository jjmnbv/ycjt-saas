package com.beitu.saas.rest.controller.h5.response;

import com.beitu.saas.borrower.domain.SaasBorrowerIdentityInfoVo;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 下午4:54
 * @description
 */
@ApiModel(value = "风控模块身份证信息")
public class CreditIdentityInfoResponse implements ResponseData {

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

    public CreditIdentityInfoResponse(SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo, String prefixUrl) {
        if (saasBorrowerIdentityInfoVo != null) {
            String frontUrl = saasBorrowerIdentityInfoVo.getFrontUrl();
            if (StringUtils.isNotEmpty(frontUrl)) {
                if (!frontUrl.contains("http:") && !frontUrl.contains("https:")) {
                    frontUrl = prefixUrl + frontUrl;
                }
                this.frontUrl = frontUrl;
            }
            String backUrl = saasBorrowerIdentityInfoVo.getBackUrl();
            if (StringUtils.isNotEmpty(backUrl)) {
                if (!backUrl.contains("http:") && !backUrl.contains("https:")) {
                    backUrl = prefixUrl + backUrl;
                }
                this.backUrl = backUrl;
            }
            String holdUrl = saasBorrowerIdentityInfoVo.getHoldUrl();
            if (StringUtils.isNotEmpty(holdUrl)) {
                if (!holdUrl.contains("http:") && !holdUrl.contains("https:")) {
                    holdUrl = prefixUrl + holdUrl;
                }
                this.holdUrl = holdUrl;
            }
        }
    }

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