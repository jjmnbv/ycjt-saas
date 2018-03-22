package com.beitu.saas.risk.handler.platform.zmxy;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2017-03-18 14:24
 */
public class ZmxyParams implements Serializable {
    private String appId;
    private String privateKey;
    private String zhimaPublicKey;

    public ZmxyParams(String appId, String privateKey, String zhimaPublicKey) {
        this.appId = appId;
        this.privateKey = privateKey;
        this.zhimaPublicKey = zhimaPublicKey;
    }

    public String getAppId() {
        return appId;
    }

    public ZmxyParams setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public ZmxyParams setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public String getZhimaPublicKey() {
        return zhimaPublicKey;
    }

    public ZmxyParams setZhimaPublicKey(String zhimaPublicKey) {
        this.zhimaPublicKey = zhimaPublicKey;
        return this;
    }
}
