package com.beitu.saas.app.enums;

public enum OpenApiOrderPushFromTypeEnum {
    
    YCJT_APP(1L, "ycjtapp", "洋葱借条App");

    private Long type;
    
    private String code;

    private String desc;
    
    OpenApiOrderPushFromTypeEnum(Long type, String code, String desc) {
        this.type = type;
        this.code = code;
        this.desc = desc;
    }
    
    public Long getType() {
        return type;
    }
    
    public void setType(Long type) {
        this.type = type;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
}