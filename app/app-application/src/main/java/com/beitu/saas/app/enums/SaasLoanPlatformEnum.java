package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/30 下午4:29
 * @description
 */
public enum SaasLoanPlatformEnum implements MsgCodeEnum {

    JIN_JIE_DAO(1, "今借到", "jinjiedao"),
    MI_FANG(2, "米房", "mifang"),
    JIE_DAI_BAO(3, "借贷宝", "jiedaibao"),
    WU_YOU_JIE_TIAO(4, "无忧借条", "wuyoujietiao");

    private Integer code;

    private String msg;

    private String website;

    SaasLoanPlatformEnum(Integer code, String msg, String website) {
        this.code = code;
        this.msg = msg;
        this.website = website;
    }

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public static SaasLoanPlatformEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (SaasLoanPlatformEnum platformEnum : SaasLoanPlatformEnum.values()) {
            if (platformEnum.getCode().equals(code)) {
                return platformEnum;
            }
        }
        return null;
    }

}
