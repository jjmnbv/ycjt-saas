package com.beitu.saas.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/3
 * Time: 上午11:15
 */
public class LoanStateDetailVo {
    private BigDecimal monthLoanTotalAmount;
    private BigDecimal monthDestroyTotalAmount;
    private Integer monthLoanPersonNum;
    private String month;

    public String getMonth() {
        return month;
    }

    public LoanStateDetailVo setMonth(String month) {
        this.month = month;
        return this;
    }

    public BigDecimal getMonthLoanTotalAmount() {
        return monthLoanTotalAmount;
    }

    public LoanStateDetailVo setMonthLoanTotalAmount(BigDecimal monthLoanTotalAmount) {
        this.monthLoanTotalAmount = monthLoanTotalAmount;
        return this;
    }

    public BigDecimal getMonthDestroyTotalAmount() {
        return monthDestroyTotalAmount;
    }

    public LoanStateDetailVo setMonthDestroyTotalAmount(BigDecimal monthDestroyTotalAmount) {
        this.monthDestroyTotalAmount = monthDestroyTotalAmount;
        return this;
    }

    public Integer getMonthLoanPersonNum() {
        return monthLoanPersonNum;
    }

    public LoanStateDetailVo setMonthLoanPersonNum(Integer monthLoanPersonNum) {
        this.monthLoanPersonNum = monthLoanPersonNum;
        return this;
    }
}
