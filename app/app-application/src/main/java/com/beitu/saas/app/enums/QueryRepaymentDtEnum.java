package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/28 上午11:08
 * @description
 */
public enum QueryRepaymentDtEnum implements MsgCodeEnum {

    ALL(1, "全部逾期", null, 0),
    DUE_TODAY(2, "今日到期", 0, 1),
    DUE_NEARLY_THREE_DAYS(3, "近3天到期", 0, 3),
    TODAY_OVERDUE(4, "今日逾期", -1, 0),
    OVERDUE_3_DAYS(5, "逾期3天", -3, 0),
    OVERDUE_7_DAYS(6, "逾期7天", -7, 0),
    OVERDUE_15_DAYS(7, "逾期15天", -15, 0),
    OVERDUE_15_30_DAYS(8, "逾期15-30天", -30, -15),
    OVERDUE_30_60_DAYS(9, "逾期30-60天", -60, -30),
    OVERDUE_60_90_DAYS(10, "逾期60-90天", -90, -60),
    OVERDUE_OVER_90_DAYS(11, "逾期90天以上", null, -90);

    QueryRepaymentDtEnum(Integer code, String msg, Integer beginParam, Integer endParam) {
        this.code = code;
        this.msg = msg;
        this.beginParam = beginParam;
        this.endParam = endParam;
    }

    private Integer code;

    private String msg;

    private Integer beginParam;

    private Integer endParam;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getBeginParam() {
        return beginParam;
    }

    public void setBeginParam(Integer beginParam) {
        this.beginParam = beginParam;
    }

    public Integer getEndParam() {
        return endParam;
    }

    public void setEndParam(Integer endParam) {
        this.endParam = endParam;
    }

    public static QueryRepaymentDtEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (QueryRepaymentDtEnum queryRepaymentDtEnum : QueryRepaymentDtEnum.values()) {
            if (queryRepaymentDtEnum.getCode().equals(code)) {
                return queryRepaymentDtEnum;
            }
        }
        return null;
    }

}