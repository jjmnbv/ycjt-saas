package com.beitu.saas.app.common;


import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/8/26
 * Time: 上午11:07
 */
public class RequestLocalInfo{
    private static final ThreadLocal<Object> currentAdmin = new ThreadLocal<>();
    private static final ThreadLocal<String> currentTraceId = new ThreadLocal<>();

    public static void putCurrentAdmin(Object user) {
        if (user == null) {
            throw new ApplicationException("Current user not exist");
        }
        reset();
        currentAdmin.set(user);
    }

    public static Object getCurrentAdmin() {
        Object user = currentAdmin.get();
        if (user == null) {
            throw new ApplicationException("Current user not exist");
        }
        return user;
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
