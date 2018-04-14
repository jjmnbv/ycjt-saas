package com.beitu.saas.risk.domain.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码
 */
public enum ErrorCodeEnums implements MsgCodeEnum {


    PARAMS_LACK(10001, "数据缺失", HttpStatus.PAYMENT_REQUIRED),
    TYPE_TRANSFORM_ERROR(10002, "类型转换异常", HttpStatus.PAYMENT_REQUIRED),

    //==============================三方数据 订阅==================================
    PLATFORM_QUERY_ERROR(20001, "三方数据查询异常", HttpStatus.PAYMENT_REQUIRED),
    PLATFORM_QUERY_PARAMS_ERROR(20002, "三方数据查询参数异常", HttpStatus.PAYMENT_REQUIRED),

    PLATFORM_SUBSTRIPTION_ERROR(20003, "三方订阅失败，请重试", HttpStatus.PAYMENT_REQUIRED),
    PLATFORM_SUBSTRIPTION_RESULT_ERROR(20004, "三方数据订阅结果异常", HttpStatus.PAYMENT_REQUIRED),
    PLATFORM_SUBSTRIPTION_PARAMS_ERROR(20005, "三方数据订阅参数异常", HttpStatus.PAYMENT_REQUIRED),
    PLATFORM_SUBSTRIPTION_NOT_APPLY_ERROR(20005, "该服务暂不支持该产品~", HttpStatus.PAYMENT_REQUIRED),

    TRIPLE_SUBSCRIPTION_TYPE_NOT_FOUND_ERROR(20006, "三方订阅类型不存在", HttpStatus.PAYMENT_REQUIRED),
    PLATFORM_SUBSCRIPTION_SAVE_DATA_ERROR(20007, "三方数据存储失败", HttpStatus.PAYMENT_REQUIRED),
    PLATFORM_SUBSTRIPTION_NEED_VERIFY_CODE(20008, "请输入短信验证码", HttpStatus.PAYMENT_REQUIRED),

    //==============================三方数据 查询==================================
    TRIPLE_QUERY_NOT_FOUND_ERROR(60000, "三方数据查询不存在", HttpStatus.PAYMENT_REQUIRED),
    TRIPLE_QUERY_ERROR(60001, "三方数据查询异常", HttpStatus.PAYMENT_REQUIRED),

    //==============================运营商 认证==================================
    CARRIER_TIMEOUT_ERROR(30000, "运营商授权异常，请重试", HttpStatus.PAYMENT_REQUIRED),
    CARRIER_NOT_CALL_RECORDS(30001, "通话记录缺失", HttpStatus.PAYMENT_REQUIRED),
    CARRIER_NOT_USER_INFO(30002, "运营商个人信息缺失", HttpStatus.PAYMENT_REQUIRED),
    CARRIER_STATUS_ERROR(30003, "爬取状态异常,请重试", HttpStatus.PAYMENT_REQUIRED),
    CARRIER_PARAMS_ERROR(30004, "信息格式错误，请联系客服", HttpStatus.PAYMENT_REQUIRED),
    CARRIER_CACHE_ERROR(30005, "缓存信息丢失，请重试", HttpStatus.PAYMENT_REQUIRED),

    CARRIER_TYPE_NOT_FOUND_ERROR(30007, "运营商服务类型不存在", HttpStatus.PAYMENT_REQUIRED),

    //==============================公积金社保==================================
    SOCIAL_TIMEOUT_ERROR(40000, "三方授权异常，请重试", HttpStatus.PAYMENT_REQUIRED),
    SOCIAL_CACHE_ERROR(40001, "缓存信息丢失，请重试", HttpStatus.PAYMENT_REQUIRED),
    SOCIAL_TYPE_NOT_FOUND_ERROR(40002, "服务类型未发现，请联系客服", HttpStatus.PAYMENT_REQUIRED),
    SOCIAL_PARAMS_ERROR(40003, "信息格式错误，请联系客服", HttpStatus.PAYMENT_REQUIRED),

    //====================================业务========================================
    CALLBACK_ERROR(50000, "风控结果回调异常", HttpStatus.PAYMENT_REQUIRED),
    RULE_RESULT_LEVEL_ERROR(50001, "风控审核异常", HttpStatus.PAYMENT_REQUIRED),

    RISK_ENGINE_DECISION_RESULT_IS_NULL(50001, "决策引擎结果是空", HttpStatus.PAYMENT_REQUIRED);


    private Integer code;
    private String desc;
    private HttpStatus httpStatus;


    private ErrorCodeEnums(Integer code, String desc, HttpStatus httpStatus) {
        this.code = code;
        this.desc = desc;
        this.httpStatus = httpStatus;
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

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMsg() {
        return getDesc();
    }
}
