package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:52
 */
public class DataDashboardLoanShowVo implements ResponseData {
    @ApiModelProperty(value = "放款数据")
    private LoanDataDetailShowVo loanDataDetailVo;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal merchantBalance = BigDecimal.ZERO;

    @ApiModelProperty(value = "剩余点券数")
    private Long merchantCredit = 0l;

    @ApiModelProperty(value = "剩余短信数")
    private Long merchantSms = 0l;

    @ApiModelProperty(value = "放款数据")
    private List<LoanStateDetailShowVo> loanStateDetailShowVos;

    public LoanDataDetailShowVo getLoanDataDetailVo() {
        return loanDataDetailVo;
    }

    public DataDashboardLoanShowVo setLoanDataDetailVo(LoanDataDetailShowVo loanDataDetailVo) {
        this.loanDataDetailVo = loanDataDetailVo;
        return this;
    }

    public BigDecimal getMerchantBalance() {
        return merchantBalance;
    }

    public DataDashboardLoanShowVo setMerchantBalance(BigDecimal merchantBalance) {
        this.merchantBalance = merchantBalance;
        return this;
    }

    public Long getMerchantCredit() {
        return merchantCredit;
    }

    public DataDashboardLoanShowVo setMerchantCredit(Long merchantCredit) {
        this.merchantCredit = merchantCredit;
        return this;
    }

    public Long getMerchantSms() {
        return merchantSms;
    }

    public DataDashboardLoanShowVo setMerchantSms(Long merchantSms) {
        this.merchantSms = merchantSms;
        return this;
    }

    public List<LoanStateDetailShowVo> getLoanStateDetailShowVos() {
        return loanStateDetailShowVos;
    }

    public DataDashboardLoanShowVo setLoanStateDetailShowVos(List<LoanStateDetailShowVo> loanStateDetailShowVos) {
        this.loanStateDetailShowVos = loanStateDetailShowVos;
        return this;
    }
}
