package com.beitu.saas.sms.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linchengyu on 17/6/16.
 */
public enum VerifyCodeTypeEnum {
    
    REGISTER("SAAS_0001", "register", "您本次登入的短信验证码为{##VERIFY_CODE##}，请及时进行登入");
    
    private String type;
    
    private String name;
    
    private String desc;
    
    VerifyCodeTypeEnum(String type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }
    
    public String getType() {
        return type;
    }
    
    public VerifyCodeTypeEnum setType(String type) {
        this.type = type;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    public VerifyCodeTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public VerifyCodeTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
    
    public static List<String> getNameList() {
        List<String> list = new ArrayList<>();
        for (VerifyCodeTypeEnum enumObj : VerifyCodeTypeEnum.values()) {
            list.add(enumObj.getName());
        }
        return list;
    }
    
    public static VerifyCodeTypeEnum getEnumByType(Integer type) {
        VerifyCodeTypeEnum verifycodeTypeEnum = null;
        for (VerifyCodeTypeEnum enumObj : VerifyCodeTypeEnum.values()) {
            if (enumObj.getType().equals(type)) {
                verifycodeTypeEnum = enumObj;
                break;
            }
        }
        
        return verifycodeTypeEnum;
    }
    
    public static VerifyCodeTypeEnum getEnumByName(String name) {
        VerifyCodeTypeEnum verifycodeTypeEnum = null;
        for (VerifyCodeTypeEnum enumObj : VerifyCodeTypeEnum.values()) {
            if (enumObj.getName().equals(name)) {
                verifycodeTypeEnum = enumObj;
                break;
            }
        }
        return verifycodeTypeEnum;
    }
}
