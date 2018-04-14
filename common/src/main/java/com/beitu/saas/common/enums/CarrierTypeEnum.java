package com.beitu.saas.common.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午7:49
 */
public enum CarrierTypeEnum {
    FIVE_ONE_CARRIER(Integer.valueOf(0), "51运营商"),
    FIVE_ONE_OPTIMIZE_CARRIER(Integer.valueOf(1), "51优化运营商"),
    TONGDUN_CARRIER(Integer.valueOf(2), "同盾运营商"),
    RONG_THREE_CARRIER(Integer.valueOf(3), "融360");

    private final Integer value;
    private final String name;

    private CarrierTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

}
