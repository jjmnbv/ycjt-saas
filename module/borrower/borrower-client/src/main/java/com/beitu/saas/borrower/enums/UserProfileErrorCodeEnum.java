package com.beitu.saas.borrower.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * Created by linchengyu on 17/3/6.
 */
public enum UserProfileErrorCodeEnum implements ErrorCodeEnum {

    USER_PROFILE_ENUM_TYPE_NOT_EXIST(61001, "选择类型参数有误", HttpStatus.OK),
    USER_PROFILE_ID_DATA_TYPE_NOT_EXIST(61002, "类型参数不存在", HttpStatus.OK),
    USER_PROFILE_BIND_CARD_INFO_NOT_COMPLETE(61003, "卡信息不完整", HttpStatus.OK),
    USER_PROFILE_BIND_CARD_ERROR_MESSAGE(61004, "绑卡异常", HttpStatus.OK),
    USER_PROFILE_BIND_CARD_NUMBER_NOT_MATCH(61005, "开户行与银行卡号不匹配，请重新填写", HttpStatus.OK),
    USER_PROFILE_BIND_CARD_BINDED(61006, "签约成功", HttpStatus.OK),
    USER_PROFILE_BANK_CODE_NOT_EXIST(61007, "银行代码不存在", HttpStatus.OK),
    USER_PROFILE_BIND_CARD_FAIL(61008, "签约失败", HttpStatus.OK),
    USER_PROFILE_BIND_CARD_NOT_EXIST(73001, "未绑定银行卡", HttpStatus.OK),
    USER_PROFILE_REAL_NAME_NOT_EXIST(61010, "无法获取用户实名信息", HttpStatus.OK),
    USER_PROFILE_51_BIND_RESPONSE_ERROR(61011, "绑卡服务异常", HttpStatus.OK),
    USER_PROFILE_FACE_NEED_WAIT(61012, "人脸识别次数超限", HttpStatus.OK),
    USER_PROFILE_SM_DATA_ERROR(61013, "数美数据异常", HttpStatus.OK),
    USER_PROFILE_NOT_ALLOW_MODIFY_PROFILE(61014, "订单处理中，请稍后完善信息", HttpStatus.OK),
    USER_PROFILE_BIND_TYPE_NOT_EXIST(61015, "银行卡绑定类型不存在", HttpStatus.OK),
    USER_PROFILE_BIND_LL_ERROR(61016, "认证失败，请稍后再试", HttpStatus.OK),
    USER_PROFILE_IDENTITY_NO_IS_EXIST(61017, "该身份证号已被占用", HttpStatus.OK),
    USER_PROFILE_IDENTITY_NO_ILLEGAL(61018, "该身份证号不合法", HttpStatus.OK),
    USER_PROFILE_BASE_SAVE_FAIL(61019, "用户基础信息保存失败", HttpStatus.OK),
    USER_PROFILE_WORK_SAVE_FAIL(61020, "用户工作信息保存失败", HttpStatus.OK),
    USER_PROFILE_EMERGENCY_CONTACT_SAVE_FAIL(61021, "用户紧急联系人信息保存失败", HttpStatus.OK),
    USER_PROFILE_EMERGENCY_CONTACT_ENUM_TYPE_NOT_EXIST(61022, "紧急联系人所选关系类型不存在", HttpStatus.OK),
    USER_PROFILE_NEED_BIND_BANK_CARD(61023, "请先绑定银行卡", HttpStatus.OK),
    USER_PROFILE_REAL_NAME_INCOMPLETE(61024, "用户实名信息不全，无法认证", HttpStatus.OK),
    USER_PROFILE_MOBILE_NOT_MATCH(61025, "认证手机号与用户注册手机号不一致", HttpStatus.OK),
    USER_PROFILE_ZM_HAS_AUTHORIZED(61026, "实名认证已通过，请勿重复认证", HttpStatus.OK),
    USER_PROFILE_ZM_AUTHORIZE_ERROR(61027, "实名认证异常,请稍后再试", HttpStatus.OK),
    USER_PROFILE_ZM_NOT_EXIST(61028, "实名信息不正确或未开通支付宝", HttpStatus.OK),
    USER_PROFILE_ZM_IDENTITY_NO_HAS_EXIST(61029, "该持卡人信息已存在", HttpStatus.OK),
    USER_PROFILE_BINDED_IDENTITY_NO_MATCH(61030, "持卡人信息与已绑定卡片的持卡人信息不一致", HttpStatus.OK),
    USER_PROFILE_CARD_NUMBER_HAS_EXIST(61031, "该卡号已被绑定", HttpStatus.OK),
    USER_PROFILE_BANK_NAME_IS_EMPTY(61032, "请输入持卡人姓名", HttpStatus.OK),
    USER_PROFILE_BANK_ID_NO_IS_EMPTY(61033, "请输入身份证号", HttpStatus.OK),
    USER_PROFILE_NOT_BANK_CARD_NEED_CONFIRM(61034, "无待绑定银行卡", HttpStatus.OK),
    USER_PROFILE_BANK_CARD_DETAIL_ERROR(61035, "无法获取银行卡详情", HttpStatus.OK),
    USER_PROFILE_REAL_NAME_NOT_CORRECT(61036, "实名信息不符，无法操作", HttpStatus.OK),
    USER_PROFILE_BANK_CARD_NOT_EXIST(61037, "该银行卡不存在", HttpStatus.OK),
    USER_PROFILE_IDENTITY_NO_NEED_ADULT(61038, "本业务暂时只对18周岁以上用户开放", HttpStatus.OK),
    USER_PROFILE_NEED_ZM_FACE(61039, "请进行人脸安全识别", HttpStatus.OK),
    USER_PROFILE_ID_DATA_NOT_COMPLETE(61040, "缺少必要输入参数", HttpStatus.OK),
    USER_PROFILE_ID_NO_NEED_AUTH(61041, "无需重复认证", HttpStatus.OK),
    USER_PROFILE_ID_NEED_AUTH_FRONT(61042, "请先认证身份证正面", HttpStatus.OK),
    USER_PROFILE_ID_NEED_RE_DO(61043, "身份证认证采集图像异常，请重新认证", HttpStatus.OK),
    USER_PROFILE_FACE_TODAY_TIME_EXCEEDED(61044, "当天人脸认证次数超限，请明天再试", HttpStatus.OK),
    USER_PROFILE_AUTH_SHUT_DOWN(61045, "该功能临时关闭", HttpStatus.OK);
    
    private Integer code;
    
    private String msg;
    
    private HttpStatus httpStatus;
    
    UserProfileErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
    
    @Override
    public Integer getCode() {
        return code;
    }
    
    public UserProfileErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }
    
    public UserProfileErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    public UserProfileErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
