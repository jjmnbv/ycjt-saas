package com.beitu.saas.app.application.order.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:52
 */
public class DataDashboardShowVo {

    private LoanDataDetailShowVo loanDataDetailVo;

    private BigDecimal merchantBalance;

    private  Long merchantCredit;

    private  Long merchantSms;

    private List<NoRepayOrderShowVo> noRepayOrderShowVos;

    private List<OverdueOrderShowVo> overdueOrderShowVos;

    private List<LoanStateDetailShowVo> loanStateDetailShowVos;

    public LoanDataDetailShowVo getLoanDataDetailVo() {
        return loanDataDetailVo;
    }

    public DataDashboardShowVo setLoanDataDetailVo(LoanDataDetailShowVo loanDataDetailVo) {
        this.loanDataDetailVo = loanDataDetailVo;
        return this;
    }

    public BigDecimal getMerchantBalance() {
        return merchantBalance;
    }

    public DataDashboardShowVo setMerchantBalance(BigDecimal merchantBalance) {
        this.merchantBalance = merchantBalance;
        return this;
    }

    public Long getMerchantCredit() {
        return merchantCredit;
    }

    public DataDashboardShowVo setMerchantCredit(Long merchantCredit) {
        this.merchantCredit = merchantCredit;
        return this;
    }

    public Long getMerchantSms() {
        return merchantSms;
    }

    public DataDashboardShowVo setMerchantSms(Long merchantSms) {
        this.merchantSms = merchantSms;
        return this;
    }

    public List<NoRepayOrderShowVo> getNoRepayOrderShowVos() {
        return noRepayOrderShowVos;
    }

    public DataDashboardShowVo setNoRepayOrderShowVos(List<NoRepayOrderShowVo> noRepayOrderShowVos) {
        this.noRepayOrderShowVos = noRepayOrderShowVos;
        return this;
    }

    public List<OverdueOrderShowVo> getOverdueOrderShowVos() {
        return overdueOrderShowVos;
    }

    public DataDashboardShowVo setOverdueOrderShowVos(List<OverdueOrderShowVo> overdueOrderShowVos) {
        this.overdueOrderShowVos = overdueOrderShowVos;
        return this;
    }

    public List<LoanStateDetailShowVo> getLoanStateDetailShowVos() {
        return loanStateDetailShowVos;
    }

    public DataDashboardShowVo setLoanStateDetailShowVos(List<LoanStateDetailShowVo> loanStateDetailShowVos) {
        this.loanStateDetailShowVos = loanStateDetailShowVos;
        return this;
    }
}
