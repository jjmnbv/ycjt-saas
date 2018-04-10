package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/3
 * Time: 上午11:15
 */
public class LoanStateDetailShowVo implements ResponseData {
    private BigDecimal monthLoanTotalAmount=BigDecimal.ZERO;
    private BigDecimal monthDestroyTotalAmount=BigDecimal.ZERO;
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
}
