package com.beitu.saas.risk.domain.enums.triple;

import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;

public enum TripleServiceTypeEnum {
    TONGDUN_REPORT_ID(1, "tongdun_report_id", "同盾报告id"),
    TONGDUN_REPORT_QUERY(2, "tongdun_report_query", "同盾报告查询"),

    ZMXY_SCORE(8, "zmxy_score", "芝麻分查询"),
    ZMXY_AUTHORIZATION(9, "zmxy_authorization", "芝麻认证接口"),
    ZMXY_IVS_WATCH(10, "zmxy_ivs_watch", "芝麻ivs+watch"),
    ZMXY_ANTI_FRAUD(11, "zmxy_anti_fraud", "芝麻反欺诈(3个)"),

    CARRIER_SUBMIT_ACCOUNT(17, "carrier_submit_account", "运营商提交账号"),
    CARRIER_SUBMIT_VERITY(18, "carrier_submit_verity", "运营商提交验证码"),
    CARRIER_QUERY_STATUS(19, "carrier_query_status", "运营商状态轮询"),
    CARRIER_QUERY_REPORT(20, "carrier_query_report", "运营商数据查询"),

    CARRIER_REQUEST_URL(21, "carrier_request_url", "h5运营商链接"),
    CARRIER_TASKID_INFO(29, "carrier_taskid_info", "h5运营商信息获取");


    private Integer type;
    private String name;
    private String desc;

    TripleServiceTypeEnum(Integer type, String name, String desc) {
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

    public static TripleServiceTypeEnum getEnumByType(Integer type) {
        for (TripleServiceTypeEnum tripleServiceTypeEnum : TripleServiceTypeEnum.values()) {
            if (tripleServiceTypeEnum.getType().equals(type)) {
                return tripleServiceTypeEnum;
            }
        }
        throw new BizException(ErrorCodeEnums.TYPE_TRANSFORM_ERROR);
    }

}
