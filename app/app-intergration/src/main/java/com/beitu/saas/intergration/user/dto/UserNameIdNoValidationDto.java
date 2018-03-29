package com.beitu.saas.intergration.user.dto;

import com.beitu.saas.intergration.user.enums.UserNameIdNoValidationCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class UserNameIdNoValidationDto implements Serializable {
    
    private static final long serialVersionUID = -8564920145652112526L;
    
    private Integer code;
    
    private String msg;
    
    public UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }
    
    public UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum codeEnum, String msg) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        if (StringUtils.isNotEmpty(msg)) {
            this.msg = msg;
        }
    }
    
    public Integer getCode() {
        return code;
    }
    
    public UserNameIdNoValidationDto setCode(Integer code) {
        this.code = code;
        return this;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public UserNameIdNoValidationDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
