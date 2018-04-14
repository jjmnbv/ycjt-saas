/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.ro;

import java.io.Serializable;

/**
 * app info
 *
 * @author liting
 * @version $Id: AppInfoRO.java, v 0.1 2017年11月30日 下午3:46:29 liting Exp $
 * @name app info
 */
public class AppInfoRO implements Serializable {

    /**
     * : detail description
     */
    private static final long serialVersionUID = -4164418028973515780L;

    private Long appId;
    /**
     * app名
     */
    private String appName;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
