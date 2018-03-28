package com.beitu.saas.finance.client.domain;

import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.NoRepayOrderVo;
import com.beitu.saas.order.vo.OverdueOrderVo;

import java.math.BigDecimal;
import java.util.List;

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

    private List<NoRepayOrderVo> noRepayOrderVos;

    private List<OverdueOrderVo> overdueOrderVos;

    public List<NoRepayOrderVo> getNoRepayOrderVos() {
        return noRepayOrderVos;
    }

    public DataDashboardVo setNoRepayOrderVos(List<NoRepayOrderVo> noRepayOrderVos) {
        this.noRepayOrderVos = noRepayOrderVos;
        return this;
    }

    public List<OverdueOrderVo> getOverdueOrderVos() {
        return overdueOrderVos;
    }

    public DataDashboardVo setOverdueOrderVos(List<OverdueOrderVo> overdueOrderVos) {
        this.overdueOrderVos = overdueOrderVos;
        return this;
    }

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
