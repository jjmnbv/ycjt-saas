package com.beitu.saas.app.common;


import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;

import java.util.UUID;

/**
 * @author xiaochong
 * @create 2018/3/22 下午5:57
 * @description
 */
public class RequestLocalInfo {
    private static final ThreadLocal<RequestUserInfo> currentAdmin = new ThreadLocal<>();
    private static final ThreadLocal<String> currentTraceId = new ThreadLocal<>();

    public static void putCurrentAdmin(RequestUserInfo requestUserInfo) {
        if (requestUserInfo == null) {
            throw new ApplicationException("Current borrower not exist");
        }
        reset();
        currentAdmin.set(requestUserInfo);
    }

    public static RequestUserInfo getCurrentAdmin() {

        RequestUserInfo requestUserInfo = currentAdmin.get();
        if (requestUserInfo == null) {
            throw new ApplicationException("Current borrower not exist");
        }
        return requestUserInfo;
    }

    public static String getCurrentTraceId() {
        String traceId = currentTraceId.get();
        if (StringUtils.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
            currentTraceId.set(traceId);
        }

        return traceId;
    }

    public static void reset() {
        currentAdmin.remove();
        currentTraceId.remove();
    }
}
