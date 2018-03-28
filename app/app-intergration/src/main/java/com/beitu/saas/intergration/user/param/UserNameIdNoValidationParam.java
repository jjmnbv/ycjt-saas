package com.beitu.saas.intergration.user.param;

import com.beitu.saas.common.utils.identityNumber.IdentityNumberUtil;
import org.apache.commons.lang3.StringUtils;

public class UserNameIdNoValidationParam {
    
    private String name;
    
    private String identityNo;
    
    public UserNameIdNoValidationParam(String name, String identityNo) {
        this.name = name;
        this.identityNo = identityNo;
    }
    
    public String getName() {
        return name;
    }
    
    public UserNameIdNoValidationParam setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getIdentityNo() {
        return identityNo;
    }
    
    public UserNameIdNoValidationParam setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
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
        
        return null;
    }
    
}
