package com.beitu.saas.app.application.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class LoanDataDetailShowVo {
    private BigDecimal loanTotalAmount;

    private BigDecimal todayLoanTotalAmount;

    private BigDecimal destroyTotalAmount;

    private BigDecimal totalDestroyTotalAmount;

    private BigDecimal noRepayTotalAmount;

    private BigDecimal overdueTotalAmount;

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

    public BigDecimal getTotalDestroyTotalAmount() {
        return totalDestroyTotalAmount;
    }

    public LoanDataDetailShowVo setTotalDestroyTotalAmount(BigDecimal totalDestroyTotalAmount) {
        this.totalDestroyTotalAmount = totalDestroyTotalAmount;
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
}
