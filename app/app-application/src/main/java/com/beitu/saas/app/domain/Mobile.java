package com.beitu.saas.app.domain;


import com.fgqj.youjie.common.utils.MobileUtil;
import com.fqgj.common.api.exception.ApiErrorException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by linchengyu on 17/6/16.
 */
public class Mobile {

    private String mobile;

    public Mobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return;
        }

        if (!MobileUtil.isMobile(mobile)) {
            throw new ApiErrorException("手机号码不正确");
        }

        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }
}
