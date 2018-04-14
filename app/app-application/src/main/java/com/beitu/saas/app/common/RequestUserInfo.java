package com.beitu.saas.app.common;

import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.fqgj.exception.common.ApplicationException;

/**
 * @author xiaochong
 * @create 2018/3/22 下午5:57
 * @description
 */
public class RequestUserInfo {

    private RequestBasicInfo requestBasicInfo;
    private Object user;

    public SaasAdmin getSaasAdmin() {
        SaasAdmin saasAdmin;
        try {
            saasAdmin = (SaasAdmin) user;
        } catch (Exception e) {
            throw new ApplicationException("illegal request");
        }
        return saasAdmin;
    }

    public SaasBorrowerVo getSaasBorrower() {
        SaasBorrowerVo saasBorrowerVo;
        try {
            saasBorrowerVo = (SaasBorrowerVo) user;
        } catch (Exception e) {
            throw new ApplicationException("illegal request");
        }
        return saasBorrowerVo;
    }

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
