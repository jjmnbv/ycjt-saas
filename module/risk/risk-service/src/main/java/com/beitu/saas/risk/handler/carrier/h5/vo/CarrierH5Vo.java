package com.beitu.saas.risk.handler.carrier.h5.vo;


import java.io.Serializable;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/10/30 0030 下午 8:21
 */
public class CarrierH5Vo implements Serializable{
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号(运营商报告请与传给抓取服务的手机号一致)
     */
    private String phone;
    /**
     * 身份证号码
     */
    private String idNumber;
    /**
     * 调用方生成的用户ID（调用方定义）
     */
    private String userCode;
    /**
     * H5重定向地址get
     */
    private String returnUrl;
    /**
     * 回调app端地址
     */
    private String appUrl;

    public String getName() {
        return name;
    }

    public CarrierH5Vo setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CarrierH5Vo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public CarrierH5Vo setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public CarrierH5Vo setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public CarrierH5Vo setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
        return this;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public CarrierH5Vo setAppUrl(String appUrl) {
        this.appUrl = appUrl;
        return this;
    }
}
