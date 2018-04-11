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
    private BigDecimal loanTotalAmount = BigDecimal.ZERO;

    private BigDecimal todayLoanTotalAmount = BigDecimal.ZERO;

    private BigDecimal destroyTotalAmount = BigDecimal.ZERO;

    private BigDecimal todayDestroyTotalAmount = BigDecimal.ZERO;

    private BigDecimal noRepayTotalAmount = BigDecimal.ZERO;

    private BigDecimal overdueTotalAmount = BigDecimal.ZERO;

    private BigDecimal todayOverdueTotalAmount = BigDecimal.ZERO;

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

    public BigDecimal getTodayDestroyTotalAmount() {
        return todayDestroyTotalAmount;
    }

    public LoanDataDetailVo setTodayDestroyTotalAmount(BigDecimal todayDestroyTotalAmount) {
        this.todayDestroyTotalAmount = todayDestroyTotalAmount;
        return this;
    }

    public BigDecimal getTodayOverdueTotalAmount() {
        return todayOverdueTotalAmount;
    }

    public LoanDataDetailVo setTodayOverdueTotalAmount(BigDecimal todayOverdueTotalAmount) {
        this.todayOverdueTotalAmount = todayOverdueTotalAmount;
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
