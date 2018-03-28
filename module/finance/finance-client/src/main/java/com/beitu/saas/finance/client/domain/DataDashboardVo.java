package com.beitu.saas.finance.client.domain;

import com.beitu.saas.order.vo.LoanDataDetailVo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:52
 */
public class DataDashboardVo {
    private LoanDataDetailVo loanDataDetailVo;

    private BigDecimal merchantBalance;

    private  Long merchantCredit;

    private  Long merchantSms;

    public LoanDataDetailVo getLoanDataDetailVo() {
        return loanDataDetailVo;
    }

    public DataDashboardVo setLoanDataDetailVo(LoanDataDetailVo loanDataDetailVo) {
        this.loanDataDetailVo = loanDataDetailVo;
        return this;
    }

    public BigDecimal getMerchantBalance() {
        return merchantBalance;
    }

    public DataDashboardVo setMerchantBalance(BigDecimal merchantBalance) {
        this.merchantBalance = merchantBalance;
        return this;
    }

    public Long getMerchantCredit() {
        return merchantCredit;
    }

    public DataDashboardVo setMerchantCredit(Long merchantCredit) {
        this.merchantCredit = merchantCredit;
        return this;
    }

    public Long getMerchantSms() {
        return merchantSms;
    }

    public DataDashboardVo setMerchantSms(Long merchantSms) {
        this.merchantSms = merchantSms;
        return this;
    }
}
