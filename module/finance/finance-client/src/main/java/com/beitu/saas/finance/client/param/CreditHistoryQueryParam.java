package com.beitu.saas.finance.client.param;

import java.util.Date;

/**
 * @author xiaochong
 * @create 2018/3/28 下午9:42
 * @description
 */
public class CreditHistoryQueryParam {

    private Date startDate;

    private Date endDate;

    private Integer opType;

    private String merchantCode;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }
}
