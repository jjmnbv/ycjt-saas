/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.domain;

/**
 * 短信统计
 *
 * @name 短信统计
 * @author liting
 * @version $Id: SmsMsgStatistic.java, v 0.1 2017年12月1日 上午9:51:47 liting Exp $
 */
public class SmsMsgStatistic {

    /**
     * 业务码
     */
    private String bizCode;
    /**
     * 服务商
     */
    private String servicerCode;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 数量
     */
    private Integer num;
    public String getBizCode() {
        return bizCode;
    }
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
    public String getServicerCode() {
        return servicerCode;
    }
    public void setServicerCode(String servicerCode) {
        this.servicerCode = servicerCode;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
}
