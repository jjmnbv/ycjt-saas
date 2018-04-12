package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/31 下午3:57
 * @description
 */
public enum SaasLendRemarkEnum implements MsgCodeEnum {

    ALI_PAY(1, "支付宝"),
    WECHANT_PAY(2, "微信支付"),
    BANK_TRANSFER(3, "银行转账"),
    CASH_PAYMENTS(4, "现金支付"),
    JIEDAIBAO(5, "借贷宝"),
    YANGCONGJIETIAO(6, "洋葱借条"),
    WUYOUJIETIAO(7, "无忧借条"),;

    SaasLendRemarkEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;


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

    public static SaasLendRemarkEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (SaasLendRemarkEnum lendRemarkEnum : SaasLendRemarkEnum.values()) {
            if (lendRemarkEnum.getCode().equals(code)) {
                return lendRemarkEnum;
            }
        }
        return null;
    }

}