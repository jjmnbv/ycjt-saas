package com.beitu.saas.rest.controller.finance.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author xiaochong
 * @create 2018/3/28 下午6:34
 * @description
 */
@ApiModel
public class AccountInfoResponse implements ResponseData {

    @ApiModelProperty("账户余额")
    private BigDecimal balance;
    @ApiModelProperty("点券")
    private Long credit;
    @ApiModelProperty("短信条数")
    private Long sms;

    public AccountInfoResponse(BigDecimal balance, Long credit, Long sms) {
        this.balance = balance;
        this.credit = credit;
        this.sms = sms;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Long getSms() {
        return sms;
    }

    public void setSms(Long sms) {
        this.sms = sms;
    }
}
