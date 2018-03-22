package com.beitu.saas.sms.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linchengyu on 17/6/16.
 */
public enum VerifyCodeTypeEnum {
    
    REGISTER("YCJT_0014", "register", "您的验证码是：{0}，您正在用手机注册，请及时验证。"),
    RESET_PASSWORD("YCJT_0015", "reset_password", "您的验证码是：{0}，该验证码用于密码找回，请勿泄露验证码。"),
    BIND_DEVICE("YCJT_0016", "bind_device", "您的验证码是：{0}，您正在使用新设备进行登陆，如非本人操作，请忽略。"),
    MODIFY_MOBILE("YCJT_0017", "modify_mobile", "您的验证码是：{0}，您正在绑定当前手机号，如非本人操作，请忽略。");
    
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
