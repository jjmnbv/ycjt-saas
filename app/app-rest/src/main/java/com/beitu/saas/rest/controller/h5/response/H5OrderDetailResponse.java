package com.beitu.saas.rest.controller.h5.response;

import com.beitu.saas.app.application.order.vo.ExtendOrderDetailVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
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
    private List<ExtendOrderDetailVo> extendOrderDetailVos;

    @ApiModelProperty(value = "合同1展示信息")
    private String contractTitle1;

    @ApiModelProperty(value = "合同1url")
    private String contractUrl1;

    @ApiModelProperty(value = "合同2展示信息")
    private String contractTitle2;

    @ApiModelProperty(value = "合同2url")
    private String contractUrl2;

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

    public String getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(String realCapital) {
        this.realCapital = realCapital;
    }

    public Integer getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(Integer borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBorrowPurpose() {
        return borrowPurpose;
    }

    public void setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerIdentityCode() {
        return borrowerIdentityCode;
    }

    public void setBorrowerIdentityCode(String borrowerIdentityCode) {
        this.borrowerIdentityCode = borrowerIdentityCode;
    }

    public List<ExtendOrderDetailVo> getExtendOrderDetailVos() {
        return extendOrderDetailVos;
    }

    public void setExtendOrderDetailVos(List<ExtendOrderDetailVo> extendOrderDetailVos) {
        this.extendOrderDetailVos = extendOrderDetailVos;
    }

    public String getContractTitle1() {
        return contractTitle1;
    }

    public void setContractTitle1(String contractTitle1) {
        this.contractTitle1 = contractTitle1;
    }

    public String getContractUrl1() {
        return contractUrl1;
    }

    public void setContractUrl1(String contractUrl1) {
        this.contractUrl1 = contractUrl1;
    }

    public String getContractTitle2() {
        return contractTitle2;
    }

    public void setContractTitle2(String contractTitle2) {
        this.contractTitle2 = contractTitle2;
    }

    public String getContractUrl2() {
        return contractUrl2;
    }

    public void setContractUrl2(String contractUrl2) {
        this.contractUrl2 = contractUrl2;
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