package com.beitu.saas.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class LoanDataDetailVo {
    private BigDecimal loanTotalAmount;

    private BigDecimal todayLoanTotalAmount;

    private BigDecimal destroyTotalAmount;

    private BigDecimal totalDestroyTotalAmount;

    private BigDecimal noRepayTotalAmount;

    private BigDecimal overdueTotalAmount;

    public BigDecimal getLoanTotalAmount() {
        return loanTotalAmount;
    }

    public LoanDataDetailVo setLoanTotalAmount(BigDecimal loanTotalAmount) {
        this.loanTotalAmount = loanTotalAmount;
        return this;
    }

    public BigDecimal getTodayLoanTotalAmount() {
        return todayLoanTotalAmount;
    }

    public LoanDataDetailVo setTodayLoanTotalAmount(BigDecimal todayLoanTotalAmount) {
        this.todayLoanTotalAmount = todayLoanTotalAmount;
        return this;
    }

    public BigDecimal getDestroyTotalAmount() {
        return destroyTotalAmount;
    }

    public LoanDataDetailVo setDestroyTotalAmount(BigDecimal destroyTotalAmount) {
        this.destroyTotalAmount = destroyTotalAmount;
        return this;
    }

    public BigDecimal getTotalDestroyTotalAmount() {
        return totalDestroyTotalAmount;
    }

    public LoanDataDetailVo setTotalDestroyTotalAmount(BigDecimal totalDestroyTotalAmount) {
        this.totalDestroyTotalAmount = totalDestroyTotalAmount;
        return this;
    }

    public BigDecimal getNoRepayTotalAmount() {
        return noRepayTotalAmount;
    }

    public LoanDataDetailVo setNoRepayTotalAmount(BigDecimal noRepayTotalAmount) {
        this.noRepayTotalAmount = noRepayTotalAmount;
        return this;
    }

    public BigDecimal getOverdueTotalAmount() {
        return overdueTotalAmount;
    }

    public LoanDataDetailVo setOverdueTotalAmount(BigDecimal overdueTotalAmount) {
        this.overdueTotalAmount = overdueTotalAmount;
        return this;
    }
}
