package com.beitu.saas.app.common;

import com.fqgj.common.api.ParamsObject;

/**
 * @author xiaochong
 * @create 2018/3/22 下午5:57
 * @description
 */
public class RequestBasicInfo extends ParamsObject {
    
    private static final long serialVersionUID = 3937282672478541910L;

    private String token;
    private String channel;
    private String platform;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public void validate() {

    }
}
