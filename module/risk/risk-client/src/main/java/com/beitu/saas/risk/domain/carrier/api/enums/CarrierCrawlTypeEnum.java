package com.beitu.saas.risk.domain.carrier.api.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 对上层交互的运营商类型枚举
 */
public enum CarrierCrawlTypeEnum {

    CARRIER_51(1, "51Carrier", "51坚果平台"),
    CARRIER_TONGDUN(2, "tongDunCarrier", "同盾"),
    CARRIER_SHANGSHU(3, "shangShuCarrier", "上树");
//    CARRIER_WECASH(3, "wecashCarrier", "闪银");

    private Integer type;
    private String nickName;
    private String desc;
//
    private static List<String> nickNames = new ArrayList<>();
    static {
        CarrierCrawlTypeEnum[] types = CarrierCrawlTypeEnum.values();
        for (CarrierCrawlTypeEnum type : types) {
            nickNames.add(type.getNickName());
        }
    }

    public static CarrierCrawlTypeEnum getNextEnumByNickName(String nickName) {

        int index = nickNames.indexOf(nickName);
        if (index < 0) {
            return null;
        }
        int size = nickNames.size();

        int nextIndex = index + 1;
        if (nextIndex == size) {
            nextIndex = 0;
        }

        return getEnumByNickName(nickNames.get(nextIndex));
    }

    CarrierCrawlTypeEnum(Integer type, String nickName, String desc) {
        this.type = type;
        this.nickName = nickName;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public CarrierCrawlTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CarrierCrawlTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static CarrierCrawlTypeEnum getEnumByType(Integer type) {
        CarrierCrawlTypeEnum carrierTypeEnum = null;
        for (CarrierCrawlTypeEnum e : CarrierCrawlTypeEnum.values()) {
            if (e.getType().equals(type)) {
                carrierTypeEnum = e;
            }
        }
        return carrierTypeEnum;
    }

    public static CarrierCrawlTypeEnum getEnumByNickName(String nickName) {
        CarrierCrawlTypeEnum carrierTypeEnum = null;
        for (CarrierCrawlTypeEnum e : CarrierCrawlTypeEnum.values()) {
            if (e.getNickName().equals(nickName)) {
                carrierTypeEnum = e;
            }
        }
        return carrierTypeEnum;
    }

}
