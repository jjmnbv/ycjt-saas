package com.beitu.saas.intergration.risk.dto;

import com.beitu.saas.intergration.risk.enums.LoanPlatformQueryCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class LoanPlatformQueryDto implements Serializable {
    
    private static final long serialVersionUID = -8814504212410493067L;
    
    private Integer code;
    
    private String msg;
    
    private String data;
    
    public LoanPlatformQueryDto(LoanPlatformQueryCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }
    
    public LoanPlatformQueryDto(LoanPlatformQueryCodeEnum codeEnum, String msg) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        if (StringUtils.isNotEmpty(msg)) {
            this.msg = msg;
        }
    }
    
    public Integer getCode() {
        return code;
    }
    
    public LoanPlatformQueryDto setCode(Integer code) {
        this.code = code;
        return this;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public LoanPlatformQueryDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
    public String getData() {
        return data;
    }
    
    public LoanPlatformQueryDto setData(String data) {
        this.data = data;
        return this;
    }
}
