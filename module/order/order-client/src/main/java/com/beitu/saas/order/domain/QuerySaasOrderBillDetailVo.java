package com.beitu.saas.order.domain;

import java.util.Date;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/27 下午10:51
 * @description
 */
public class QuerySaasOrderBillDetailVo {
    /**
     * 借款人CODE
     */
    private List<String> borrowerCodeList;
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 渠道CODE
     */
    private String channelCode;
    /**
     * 订单状态
     */
    private List<Integer> orderStatusList;
    /**
     * 账单应还日查询开始时间
     */
    private Date repaymentBeginDt;
    /**
     * 账单应还日查询结束时间
     */
    private Date repaymentEndDt;

    public List<String> getBorrowerCodeList() {
        return borrowerCodeList;
    }

    public void setBorrowerCodeList(List<String> borrowerCodeList) {
        this.borrowerCodeList = borrowerCodeList;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public List<Integer> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<Integer> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public Date getRepaymentBeginDt() {
        return repaymentBeginDt;
    }

    public void setRepaymentBeginDt(Date repaymentBeginDt) {
        this.repaymentBeginDt = repaymentBeginDt;
    }

    public Date getRepaymentEndDt() {
        return repaymentEndDt;
    }

    public void setRepaymentEndDt(Date repaymentEndDt) {
        this.repaymentEndDt = repaymentEndDt;
    }
}