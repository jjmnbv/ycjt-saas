package com.beitu.saas.risk.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 柳朋朋
 * @Create 2016-08-17 15:21
 */
public enum ErrorCode {
    BIZ_UNKNOW_ERROR("20000","未知错误",404),
    BIZ_PLATFORM_QUERY_ERROR("20001", "查询异常", 404),
    BIZ_RISK_RULE_ERROR("20002", "风控规则调用失败", 404);
    private String code;
    private String desc;
    private int httpStatus;

    private ErrorCode(String code, String desc, int httpStatus) {
        this.code = code;
        this.desc = desc;
        this.httpStatus = httpStatus;
    }

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Map map() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("desc", desc);
        return result;
    }

    public Map map(String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        if (message != null) {
            result.put("desc", message + desc);
        }
        return result;
    }


    public String toString() {
        return code + ":" + desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public static void main(String[] args) {
        ErrorCode[] errorCodes = ErrorCode.values();
        for (ErrorCode errorCode : errorCodes) {
            System.out.println(errorCode);
        }
    }
}
