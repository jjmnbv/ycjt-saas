package com.beitu.saas.intergration.risk.dto;

import com.beitu.saas.intergration.risk.enums.LoanPlatformCrawlingCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class LoanPlatformCrawlingDto implements Serializable {
    
    private static final long serialVersionUID = 5475848929021372428L;
    
    private Integer code;
    
    private String msg;
    
    private String url;
    
    public LoanPlatformCrawlingDto(LoanPlatformCrawlingCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }
    
    public LoanPlatformCrawlingDto(LoanPlatformCrawlingCodeEnum codeEnum, String msg) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        if (StringUtils.isNotEmpty(msg)) {
            this.msg = msg;
        }
    }
    
    public Integer getCode() {
        return code;
    }
    
    public LoanPlatformCrawlingDto setCode(Integer code) {
        this.code = code;
        return this;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public LoanPlatformCrawlingDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
    public String getUrl() {
        return url;
    }
    
    public LoanPlatformCrawlingDto setUrl(String url) {
        this.url = url;
        return this;
    }
}
