package com.beitu.saas.borrower.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/2/26
 * Time: 下午3:53
 * 用户模块的错误代码归类
 * <p>
 */
public enum BorrowerErrorCodeEnum implements ErrorCodeEnum {

    MOBILE_EXIST(20001, "该手机号已注册", HttpStatus.OK),
    USER_PROFILE_REAL_NAME_FAILURE(20002, "用户实名信息认证失败", HttpStatus.OK),
    USER_PROFILE_NEED_REAL_NAME(20003, "请先进行实名认证", HttpStatus.OK),
    NO_ACCESS_RIGHT(20004, "用户没有权限进行此操作", HttpStatus.OK),
    USER_PROFILE_NEED_APPLICATION_INFO(20005, "请完善申请表", HttpStatus.OK),
    USER_PROFILE_NEED_PERSONAL_INFO(20006, "请完善个人信息", HttpStatus.OK),
    USER_PROFILE_NEED_IDENTITY_INFO(20007, "请先完善身份证信息", HttpStatus.OK),
    USER_PROFILE_NEED_WORK_INFO(20008, "请先完善工作信息", HttpStatus.OK),
    USER_PROFILE_NEED_EMERGENT_CONTACT(20009, "请先完善紧急联系人", HttpStatus.OK),
    USER_PROFILE_NEED_ZM_CREDIT(20010, "请先完善芝麻信用", HttpStatus.OK),
    USER_PROFILE_NEED_CARRIER_AUTHENTIC(20011, "请先完善运营商认证", HttpStatus.OK),
    USER_PROFILE_NEED_EB_INFO(20012, "请先完善电商认证", HttpStatus.OK),
    USER_PROFILE_NEED_PLATFORM_BORROW_CREDIT(20013, "请先完善多借贷平台信息", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;


    BorrowerErrorCodeEnum(Integer code, String desc, HttpStatus httpStatus) {
        this.code = code;
        this.msg = desc;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public BorrowerErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public BorrowerErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BorrowerErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
