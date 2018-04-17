package com.beitu.saas.intergration.user.param;

import com.beitu.saas.common.utils.identityNumber.IdentityNumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class UserMultiDebitParam {
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    private String name;

    private String identityNo;

    private String mobile;

    public UserMultiDebitParam(String name, String identityNo, String mobile) {
        this.name = name;
        this.identityNo = identityNo;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public UserMultiDebitParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public UserMultiDebitParam setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserMultiDebitParam setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String validate() {

        if (StringUtils.isEmpty(this.name)) {
            return "姓名为空";
        }

        if (StringUtils.isEmpty(this.identityNo)) {
            return "身份证号为空";
        }

        if (!IdentityNumberUtil.isValidatedAllIdcard(this.identityNo)) {
            return "身份证号不合法";
        }

        if (!IdentityNumberUtil.isAdultAllIdcard(this.identityNo)) {
            return "用户需满18周岁";
        }

        if (StringUtils.isEmpty(this.mobile)) {
            return "手机号为空";
        }
        if (!Pattern.matches(REGEX_MOBILE, this.mobile)) {
            return "手机号不合法!";
        }

        return null;
    }
}
