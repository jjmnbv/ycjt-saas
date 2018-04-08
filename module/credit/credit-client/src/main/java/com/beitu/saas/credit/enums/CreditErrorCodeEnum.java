package com.beitu.saas.credit.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import com.fqgj.common.api.enums.MsgCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * Created by linchengyu on 17/3/6.
 */
public enum CreditErrorCodeEnum implements ErrorCodeEnum, MsgCodeEnum {

    CREDIT_CARRIER_DATA_LACK(91001, "暂无运营商报告数据", HttpStatus.OK),
    CREDIT_BLACK_REPORT_DATA_LACK(91002, "暂无网贷黑名单报告数据", HttpStatus.OK),
    CREDIT_REPORT_NO_ACCESS(91003, "无查看该报告权限", HttpStatus.OK),
    CREDIT_ZMXY_NO_EXITS(91004, "芝麻未认证", HttpStatus.OK),
    CREDIT_ZMXY_NO_REPORT(91005, "未生成报告", HttpStatus.OK),
    CREDIT_PROFILE_NOT_COMPLETE(91006, "个人资料认证未完成，无法生成报告", HttpStatus.OK),
    CREDIT_QUERY_NOT_EXIST(91007, "无信用报告记录", HttpStatus.OK),
    CREDIT_CONTACT_CARRIER_DATA_LACK(91008, "通讯录运营商数据不全，无法解析", HttpStatus.OK),
    CREDIT_DS_ERROR(91009, "电商认证失败", HttpStatus.OK),
    CREDIT_DS_NO_EXITSR(91010, "未生成电商报告", HttpStatus.OK),
    CREDIT_DUNNING_GENERATE_ERROR(91011, "催收报告生成异常", HttpStatus.OK),
    CREDIT_BMP_GENERATE_ERROR(91012, "电话匹配报告生成异常", HttpStatus.OK),
    CREDIT_DS_SERVICE_DOWN(91013, "服务不可用", HttpStatus.OK),
    CREDIT_DS_SERVICE_BUSY(91014, "服务调用过于频繁", HttpStatus.OK),
    CREDIT_REPORT_NOT_EXIST(91015, "用户未生成报告", HttpStatus.OK),
    CREDIT_CARRIER_REPORT_EXIST(91016, "运营商已认证，无需重新认证", HttpStatus.OK),
    UPDATE_FAILURE(91017, "发生未知错误导致更新失败", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    CreditErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public CreditErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public CreditErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public CreditErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
