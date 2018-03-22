package com.beitu.saas.risk.domain.enums;

public enum TripleStatusEnum {
    init(0, "未调用"),
    exception(1, "调用异常"),
    success(2, "调用成功"),
    other(3, "other"),
    zmxy_score_fail(4, "芝麻分查询异常,可能无芝麻分"),
    qhzx_data_lack(5, "前海征信 数据丢失"),
    jy_callback_success(6,"91回调成功"),
    rong_zmxy(7,"融360订单，无芝麻数据"),
    jiuyi_stop(8,"91停止调用");


    private final Integer value;
    private final String name;

    TripleStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    //赋值
    public static TripleStatusEnum getEnumByValue(Integer value) {
        for (TripleStatusEnum tripleStatusEnum : values()) {
            if(value== tripleStatusEnum.getValue()){
                return tripleStatusEnum;
            }
        }
        return null;
    }
}
