package com.beitu.saas.sms.domain;

import java.io.Serializable;

/**
 * 短信连接信息
 */
public class SmsMsgLinkInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3299305365343900234L;
    /**
     * 接口连接
     */
	private String url;
    /**
     * 用户名
     */
	private String userName;
    /**
     * 密码
     */
	private String password;
    /**
     * 签名
     */
	private String sign;
    /**
     * 普通单条短信发送接口地址
     */
    private String interfaceUrl;
    /**
     * 批量发送地址
     */
    private String batchSendInterfaceUrl;
    /**
     * 验证码发送接口地址
     */
    private String verifyCodeInterfaceUrl;

    /**
     * 通知短信发送接口地址
     */
    private String noticeSendInterfaceUrl;

    /**
     * 营销短信
     */
    private String saleSendInterfaceUrl;

    /**
     * 短信状态查询
     */
    private String queryStatusInterfaceUrl;

    /**
     * 获取余额
     */
    private String queryBalanceInterfaceUrl;

    /**
     * 短信报告
     */
    private String queryMsgReportUrlInterfaceUrl;

    public String getQueryMsgReportUrlInterfaceUrl() {
        return queryMsgReportUrlInterfaceUrl;
    }

    public void setQueryMsgReportUrlInterfaceUrl(String queryMsgReportUrlInterfaceUrl) {
        this.queryMsgReportUrlInterfaceUrl = queryMsgReportUrlInterfaceUrl;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SmsMsgLinkInfo [url=" + url + ", userName=" + userName + ", password=" + password
               + ", sign=" + sign + "]";
    }

    public String getBatchSendInterfaceUrl() {
        return batchSendInterfaceUrl;
    }

    public void setBatchSendInterfaceUrl(String batchSendInterfaceUrl) {
        this.batchSendInterfaceUrl = batchSendInterfaceUrl;
    }

    public String getVerifyCodeInterfaceUrl() {
        return verifyCodeInterfaceUrl;
    }

    public void setVerifyCodeInterfaceUrl(String verifyCodeInterfaceUrl) {
        this.verifyCodeInterfaceUrl = verifyCodeInterfaceUrl;
    }

    public String getNoticeSendInterfaceUrl() {
        return noticeSendInterfaceUrl;
    }

    public void setNoticeSendInterfaceUrl(String noticeSendInterfaceUrl) {
        this.noticeSendInterfaceUrl = noticeSendInterfaceUrl;
    }

    public String getSaleSendInterfaceUrl() {
        return saleSendInterfaceUrl;
    }

    public void setSaleSendInterfaceUrl(String saleSendInterfaceUrl) {
        this.saleSendInterfaceUrl = saleSendInterfaceUrl;
    }

    public String getQueryStatusInterfaceUrl() {
        return queryStatusInterfaceUrl;
    }

    public void setQueryStatusInterfaceUrl(String queryStatusInterfaceUrl) {
        this.queryStatusInterfaceUrl = queryStatusInterfaceUrl;
    }

    public String getQueryBalanceInterfaceUrl() {
        return queryBalanceInterfaceUrl;
    }

    public void setQueryBalanceInterfaceUrl(String queryBalanceInterfaceUrl) {
        this.queryBalanceInterfaceUrl = queryBalanceInterfaceUrl;
    }
}
