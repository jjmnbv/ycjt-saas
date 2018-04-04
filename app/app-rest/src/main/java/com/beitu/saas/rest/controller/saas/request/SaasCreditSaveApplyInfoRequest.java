package com.beitu.saas.rest.controller.saas.request;

import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author linanjun
 * @create 2018/3/22 下午2:12
 * @description
 */
@ApiModel(description = "保存风控模块申请表信息")
public class SaasCreditSaveApplyInfoRequest extends ParamsObject {

    @ApiModelProperty(value = "借款人CODE")
    @NotBlank(message = "借款人CODE不能为空")
    private String borrowerCode;

    /**
     * 借款金额
     */
    @ApiModelProperty(value = "借款金额")
    @NotNull(message = "借款金额不能为空")
    private BigDecimal realCapital;
    /**
     * 借款年利率
     */
    @ApiModelProperty(value = "借款年利率")
    @NotNull(message = "借款年利率不能为空")
    private BigDecimal totalInterestRatio;
    /**
     * 借款意图
     */
    @ApiModelProperty(value = "借款意图")
    @NotBlank(message = "借款意图不能为空")
    private String borrowPurpose;
    /**
     * 借款天数
     */
    @ApiModelProperty(value = "借款天数")
    @NotNull(message = "借款天数")
    private Integer borrowingDuration;

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
    }

    public BigDecimal getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public String getBorrowPurpose() {
        return borrowPurpose;
    }

    public void setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
    }

    public Integer getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(Integer borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    @Override
    public void validate() {
        if (this.borrowingDuration < 0 || this.borrowingDuration > 366) {
            throw new ApiIllegalArgumentException("借款天数不正确");
        }
        if (this.realCapital.compareTo(new BigDecimal("100")) < 0 || this.realCapital.compareTo(new BigDecimal("100000")) > 0) {
            throw new ApiIllegalArgumentException("借款金额不正确");
        }
        if (this.totalInterestRatio.compareTo(new BigDecimal("0")) < 0 || this.totalInterestRatio.compareTo(new BigDecimal("24")) > 0) {
            throw new ApiIllegalArgumentException("借款年利率不正确");
        }
    }

}