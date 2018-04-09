package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class LoanDataDetailShowVo implements ResponseData {
    @ApiModelProperty(value = "总放款")
    private BigDecimal loanTotalAmount;

    @ApiModelProperty(value = "今日放款")
    private BigDecimal todayLoanTotalAmount;

    @ApiModelProperty(value = "总回收金额")
    private BigDecimal destroyTotalAmount;

    @ApiModelProperty(value = "今日回收金额")
    private BigDecimal todayDestroyTotalAmount;

    @ApiModelProperty(value = "总待回收")
    private BigDecimal noRepayTotalAmount;

    @ApiModelProperty(value = "逾期总金额")
    private BigDecimal overdueTotalAmount;

    @ApiModelProperty(value = "今日逾期金额")
    private BigDecimal todayOverdueTotalAmount;

    public BigDecimal getLoanTotalAmount() {
        return loanTotalAmount;
    }

    public LoanDataDetailShowVo setLoanTotalAmount(BigDecimal loanTotalAmount) {
        this.loanTotalAmount = loanTotalAmount;
        return this;
    }

    public BigDecimal getTodayLoanTotalAmount() {
        return todayLoanTotalAmount;
    }

    public LoanDataDetailShowVo setTodayLoanTotalAmount(BigDecimal todayLoanTotalAmount) {
        this.todayLoanTotalAmount = todayLoanTotalAmount;
        return this;
    }

    public BigDecimal getDestroyTotalAmount() {
        return destroyTotalAmount;
    }

    public LoanDataDetailShowVo setDestroyTotalAmount(BigDecimal destroyTotalAmount) {
        this.destroyTotalAmount = destroyTotalAmount;
        return this;
    }

    public BigDecimal getTodayDestroyTotalAmount() {
        return todayDestroyTotalAmount;
    }

    public LoanDataDetailShowVo setTodayDestroyTotalAmount(BigDecimal todayDestroyTotalAmount) {
        this.todayDestroyTotalAmount = todayDestroyTotalAmount;
        return this;
    }

    public BigDecimal getNoRepayTotalAmount() {
        return noRepayTotalAmount;
    }

    public LoanDataDetailShowVo setNoRepayTotalAmount(BigDecimal noRepayTotalAmount) {
        this.noRepayTotalAmount = noRepayTotalAmount;
        return this;
    }

    public BigDecimal getOverdueTotalAmount() {
        return overdueTotalAmount;
    }

    public LoanDataDetailShowVo setOverdueTotalAmount(BigDecimal overdueTotalAmount) {
        this.overdueTotalAmount = overdueTotalAmount;
        return this;
    }

    public BigDecimal getTodayOverdueTotalAmount() {
        return todayOverdueTotalAmount;
    }

    public LoanDataDetailShowVo setTodayOverdueTotalAmount(BigDecimal todayOverdueTotalAmount) {
        this.todayOverdueTotalAmount = todayOverdueTotalAmount;
        return this;
    }
}
