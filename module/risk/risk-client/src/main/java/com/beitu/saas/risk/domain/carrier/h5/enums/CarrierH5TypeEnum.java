package com.beitu.saas.risk.domain.carrier.h5.enums;

import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/10/31 0031 下午 4:13
 */
public enum CarrierH5TypeEnum {
    CARRIER_TIANJI(1, "tianjiCarrier", "天机");

    private Integer type;
    private String name;
    private String desc;



    CarrierH5TypeEnum(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static CarrierH5TypeEnum getEnumbyType(Integer type) {
        for (CarrierH5TypeEnum e: CarrierH5TypeEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    public static CarrierH5TypeEnum getEnumbyName(String name) {
        for (CarrierH5TypeEnum e: CarrierH5TypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        throw new BizException(ErrorCodeEnums.CARRIER_TYPE_NOT_FOUND_ERROR);
    }
}
