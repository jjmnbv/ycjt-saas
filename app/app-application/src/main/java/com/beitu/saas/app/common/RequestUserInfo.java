package com.beitu.saas.app.common;

/**
 * @author xiaochong
 * @create 2018/3/22 下午5:57
 * @description
 */
public class RequestUserInfo {

    private RequestBasicInfo requestBasicInfo;
    private Object user;

    public RequestBasicInfo getRequestBasicInfo() {
        return requestBasicInfo;
    }

    public void setRequestBasicInfo(RequestBasicInfo requestBasicInfo) {
        this.requestBasicInfo = requestBasicInfo;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }
}
