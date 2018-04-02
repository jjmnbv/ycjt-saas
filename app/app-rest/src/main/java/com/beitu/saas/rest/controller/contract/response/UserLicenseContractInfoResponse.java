package com.beitu.saas.rest.controller.contract.response;

import com.fqgj.common.api.ResponseData;

/**
 * @author linanjun
 * @create 2018/4/2 下午5:12
 * @description
 */
public class UserLicenseContractInfoResponse implements ResponseData {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 出借人合同姓名
     */
    private String userName;
    /**
     * 出借人合同号
     */
    private String userCode;
    /**
     * 是否显示 出借人印章
     */
    private Boolean userStamp;
    /**
     * 出借人印章URL
     */
    private String userStampUrl;
    /**
     * 落款时间
     */
    private String inscribeDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Boolean getUserStamp() {
        return userStamp;
    }

    public void setUserStamp(Boolean userStamp) {
        this.userStamp = userStamp;
    }

    public String getUserStampUrl() {
        return userStampUrl;
    }

    public void setUserStampUrl(String userStampUrl) {
        this.userStampUrl = userStampUrl;
    }

    public String getInscribeDate() {
        return inscribeDate;
    }

    public void setInscribeDate(String inscribeDate) {
        this.inscribeDate = inscribeDate;
    }

}
