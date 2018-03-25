package com.beitu.saas.rest.controller.h5.response;

import com.beitu.saas.app.application.order.vo.H5ExtendOrderDetailVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/25 下午2:07
 * @description
 */
@ApiModel(value = "用户订单详细信息")
public class H5OrderDetailResponse implements ResponseData {

    @ApiModelProperty(value = "头部展示信息")
    private String headerTitle;

    @ApiModelProperty(value = "借款金额")
    private String realCapital;

    @ApiModelProperty(value = "借款天数")
    private Integer borrowingDuration;

    @ApiModelProperty(value = "借款年利率")
    private String totalInterestRatio;

    @ApiModelProperty(value = "到期日期")
    private String repaymentDt;

    @ApiModelProperty(value = "到期应还")
    private String amount;

    @ApiModelProperty(value = "借款用途")
    private String borrowPurpose;

    @ApiModelProperty(value = "借款人姓名")
    private String borrowerName;

    @ApiModelProperty(value = "借款人身份证号")
    private String borrowerIdentityCode;

    @ApiModelProperty(value = "展期信息")
    private List<H5ExtendOrderDetailVo> extendOrderDetailVos;

    @ApiModelProperty(value = "合同展示信息")
    private String contractTitle;

    @ApiModelProperty(value = "合同url")
    private String contractUrl;

    @ApiModelProperty(value = "按钮是否展示")
    private Boolean visible;

    @ApiModelProperty(value = "按钮展示信息")
    private String buttonTitle;

    @ApiModelProperty(value = "按钮点击跳转页面")
    private String buttonUrl;

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }
}