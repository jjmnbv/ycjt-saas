package com.beitu.saas.app.application.contract.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/4/5 下午8:44
 * @description
 */
public enum ContractTypeEnum implements MsgCodeEnum {

    BORROWER_DO_LOAN_CONTRACT_SIGN(1, "借款人签署借款协议"),
    LENDER_DO_LOAN_CONTRACT_SIGN(2, "出借人签署借款协议"),
    BORROWER_DO_EXTEND_CONTRACT_SIGN(3, "借款人签署展期协议"),
    LENDER_DO_EXTEND_CONTRACT_SIGN(4, "出借人签署展期协议"),
    BORROWER_DO_AUTHORIZATION_CONTRACT_SIGN(5, "借款人签署授权书"),
    LENDER_DO_AUTHORIZATION_CONTRACT_SIGN(6, "出借人签署授权书");

    ContractTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMsg() {
        return null;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
