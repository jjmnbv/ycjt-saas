package com.beitu.saas.risk.domain.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linchengyu on 17/3/7.
 */
public enum IdentityDataTypeEnum {
    
    ID_FRONT(1, "id_front"),
    ID_BACK(2, "id_back"),
    LIVE_FACE(3, "live_face");
    
    private Integer type;
    private String desc;
    
    IdentityDataTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public Integer getType() {
        return type;
    }
    
    public IdentityDataTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public IdentityDataTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
    
    public static List<String> getDescList() {
        List<String> list = new ArrayList<>();
        for (IdentityDataTypeEnum enumObj : IdentityDataTypeEnum.values()) {
            list.add(enumObj.getDesc());
        }
        return list;
    }
    
    public static IdentityDataTypeEnum getEnumByDesc(String desc) {
        IdentityDataTypeEnum identityDataTypeEnum = null;
        for (IdentityDataTypeEnum enumObj : IdentityDataTypeEnum.values()) {
            if (enumObj.getDesc().equals(desc)) {
                identityDataTypeEnum = enumObj;
                break;
            }
        }
        return identityDataTypeEnum;
    }
}
