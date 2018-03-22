package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionUtil {
    public static String getError(Exception ex) {
        return ex.getMessage() + " : " + ExceptionUtils.getStackTrace(ex);
    }
}
