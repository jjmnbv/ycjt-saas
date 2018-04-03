package com.beitu.saas.app.application.order.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/3
 * Time: 上午11:15
 */
public class LoanStateDetailShowVo {
    private BigDecimal monthLoanTotalAmount;
    private BigDecimal monthDestroyTotalAmount;
    private Integer monthLoanPersonNum;
    private String month;

    public String getMonth() {
        return month;
    }

    public LoanStateDetailShowVo setMonth(String month) {
        this.month = month;
        return this;
    }

    public BigDecimal getMonthLoanTotalAmount() {
        return monthLoanTotalAmount;
    }

    public LoanStateDetailShowVo setMonthLoanTotalAmount(BigDecimal monthLoanTotalAmount) {
        this.monthLoanTotalAmount = monthLoanTotalAmount;
        return this;
    }

    public BigDecimal getMonthDestroyTotalAmount() {
        return monthDestroyTotalAmount;
    }

    public LoanStateDetailShowVo setMonthDestroyTotalAmount(BigDecimal monthDestroyTotalAmount) {
        this.monthDestroyTotalAmount = monthDestroyTotalAmount;
        return this;
    }

    public Integer getMonthLoanPersonNum() {
        return monthLoanPersonNum;
    }

    public LoanStateDetailShowVo setMonthLoanPersonNum(Integer monthLoanPersonNum) {
        this.monthLoanPersonNum = monthLoanPersonNum;
        return this;
    }
}
