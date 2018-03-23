package com.beitu.saas.rest.controller.h5.response;

import com.beitu.saas.borrower.domain.SaasBorrowerIdentityInfoVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * @author linanjun
 * @create 2018/3/22 下午4:54
 * @description
 */
@ApiModel(value = "风控模块身份证信息")
public class CreditIdentityInfoResponse implements ResponseData {

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

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

    public CreditIdentityInfoResponse(String orderNumb, SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo) {
        this.orderNumb = orderNumb;
        if (saasBorrowerIdentityInfoVo != null) {
            this.frontUrl = saasBorrowerIdentityInfoVo.getFrontUrl();
            this.backUrl = saasBorrowerIdentityInfoVo.getBackUrl();
            this.holdUrl = saasBorrowerIdentityInfoVo.getHoldUrl();
        }
    }

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
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