package com.beitu.saas.common.utils.excel.model;

import java.util.LinkedHashMap;

/**
 * @author linanjun
 * @create 2017/12/19 上午10:06
 * @description
 */
public class ExportCollectionSuccessOrder {

    private Long orderId; // 订单编号

    private String borrowInfo; // 借款人姓名/手机号码

    private String lenderInfo; // 出借人姓名/手机号码

    private String createdDt; // 申请借款时间

    private String realCapital; // 借款金额

    private String arrivalDt; // 借款到账时间

    private String repaymentDt; // 到期还款时间

    private String actualRepaymentDt; // 最终入账时间

    private String lateInfo; // 逾期天数(催收等级)

    private String amount; // 用户应还总额

    private String paidAmount; // 实际还款金额

    private String collectionPersonName; // 跟进人姓名

    private String orderStatus; // 订单状态

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBorrowInfo() {
        return borrowInfo;
    }

    public void setBorrowInfo(String borrowInfo) {
        this.borrowInfo = borrowInfo;
    }

    public String getLenderInfo() {
        return lenderInfo;
    }

    public void setLenderInfo(String lenderInfo) {
        this.lenderInfo = lenderInfo;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public String getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(String realCapital) {
        this.realCapital = realCapital;
    }

    public String getArrivalDt() {
        return arrivalDt;
    }

    public void setArrivalDt(String arrivalDt) {
        this.arrivalDt = arrivalDt;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public String getActualRepaymentDt() {
        return actualRepaymentDt;
    }

    public void setActualRepaymentDt(String actualRepaymentDt) {
        this.actualRepaymentDt = actualRepaymentDt;
    }

    public String getLateInfo() {
        return lateInfo;
    }

    public void setLateInfo(String lateInfo) {
        this.lateInfo = lateInfo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getCollectionPersonName() {
        return collectionPersonName;
    }

    public void setCollectionPersonName(String collectionPersonName) {
        this.collectionPersonName = collectionPersonName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static LinkedHashMap getHeader() {
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("orderId", "订单编号");
        header.put("borrowInfo", "借款人姓名/手机号码");
        header.put("lenderInfo", "出借人姓名/手机号码");
        header.put("createdDt", "申请借款时间");
        header.put("realCapital", "借款金额");
        header.put("arrivalDt", "借款到账时间");
        header.put("repaymentDt", "到期还款时间");
        header.put("actualRepaymentDt", "最终入账时间");
        header.put("lateInfo", "逾期天数(催收等级)");
        header.put("amount", "用户应还总额");
        header.put("paidAmount", "实际还款金额");
        header.put("collectionPersonName", "跟进人姓名");
        header.put("orderStatus", "订单状态");
        return header;
    }

}
