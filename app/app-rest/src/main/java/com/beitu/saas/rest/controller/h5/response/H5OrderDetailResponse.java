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
    private String headerTitle = "确认借款";

    @ApiModelProperty(value = "借款金额")
    private String realCapital = "1000.00";

    @ApiModelProperty(value = "借款天数")
    private Integer borrowingDuration = 34;

    @ApiModelProperty(value = "借款年利率")
    private String totalInterestRatio = "24%";

    @ApiModelProperty(value = "到期日期")
    private String repaymentDt = "2018-03-89";

    @ApiModelProperty(value = "到期应还")
    private String amount = "1234.00";

    @ApiModelProperty(value = "借款用途")
    private String borrowPurpose = "娶老婆";

    @ApiModelProperty(value = "借款人姓名")
    private String borrowerName = "陈傻逼";

    @ApiModelProperty(value = "借款人身份证号")
    private String borrowerIdentityCode = "334434343434343";

    @ApiModelProperty(value = "展期信息")
    private List<ExtendOrderDetailVo> extendOrderDetailVos = new ArrayList<ExtendOrderDetailVo>(2){{
        add(new ExtendOrderDetailVo());
        add(new ExtendOrderDetailVo());
    }};

    @ApiModelProperty(value = "合同展示信息")
    private String contractTitle = "借款协议";

    @ApiModelProperty(value = "合同url")
    private String contractUrl = "http://www.baidu.com";

    @ApiModelProperty(value = "按钮是否展示")
    private Boolean visible = Boolean.TRUE;

    @ApiModelProperty(value = "按钮展示信息")
    private String buttonTitle = "查看";

    @ApiModelProperty(value = "按钮点击跳转页面")
    private String buttonUrl = "http://www.baidu.com";

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