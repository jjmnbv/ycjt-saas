package com.beitu.saas.risk.handler.carrier.h5.tianji.vo;

import java.io.Serializable;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/11/2 0002 下午 5:41
 */
public class CarrierTianjiCacheVo implements Serializable{
    private static final long serialVersionUID = 7455939499313716751L;
    private String userCode;
    private String mobile;
    private String realName;
    private String idCard;
    private String appUrl;

    public String getUserCode() {
        return userCode;
    }

    public CarrierTianjiCacheVo setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CarrierTianjiCacheVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public CarrierTianjiCacheVo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public CarrierTianjiCacheVo setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public CarrierTianjiCacheVo setAppUrl(String appUrl) {
        this.appUrl = appUrl;
        return this;
    }
}
