package com.beitu.saas.common.enums;

public enum ProductTypeEnum {

    YCJT(9, "YCJT", "洋葱借条");

    private Integer type;
    private String name;
    private String desc;


    ProductTypeEnum(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public static ProductTypeEnum getEnumByType(Integer type) {
        for (ProductTypeEnum item : ProductTypeEnum.values()) {
            if (item.getType().equals(type)) {
                return item;
            }
        }
        return null;
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
}
