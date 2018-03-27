package com.beitu.saas.rest.controller.order.response;

import com.fqgj.common.api.ResponseData;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/27 上午11:21
 * @description
 */
public class PreliminaryOrderListResponse implements ResponseData {

    private List<PreliminaryOrderListVo> orderListVoList;

    public PreliminaryOrderListResponse(List<PreliminaryOrderListVo> orderListVoList) {
        this.orderListVoList = orderListVoList;
    }

    public List<PreliminaryOrderListVo> getOrderListVoList() {
        return orderListVoList;
    }

    public void setOrderListVoList(List<PreliminaryOrderListVo> orderListVoList) {
        this.orderListVoList = orderListVoList;
    }

    public static class PreliminaryOrderListVo {

        private String orderNumb;

        private String borrowerName;

        private String borrowerMobile;

        private String borrowerGender;

        private Integer borrowerAge;

        private String capital;

        private String borrowingDuration;

        private String applyDate;

        private String channelName;

        private String orderStatus;

        private String remark;

        public String getOrderNumb() {
            return orderNumb;
        }

        public void setOrderNumb(String orderNumb) {
            this.orderNumb = orderNumb;
        }

        public String getBorrowerName() {
            return borrowerName;
        }

        public void setBorrowerName(String borrowerName) {
            this.borrowerName = borrowerName;
        }

        public String getBorrowerMobile() {
            return borrowerMobile;
        }

        public void setBorrowerMobile(String borrowerMobile) {
            this.borrowerMobile = borrowerMobile;
        }

        public String getBorrowerGender() {
            return borrowerGender;
        }

        public void setBorrowerGender(String borrowerGender) {
            this.borrowerGender = borrowerGender;
        }

        public Integer getBorrowerAge() {
            return borrowerAge;
        }

        public void setBorrowerAge(Integer borrowerAge) {
            this.borrowerAge = borrowerAge;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public String getBorrowingDuration() {
            return borrowingDuration;
        }

        public void setBorrowingDuration(String borrowingDuration) {
            this.borrowingDuration = borrowingDuration;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

}