package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/31 下午3:57
 * @description
 */
public enum SaasContractEnum implements MsgCodeEnum {

    LICENSE_CONTRACT(1, "《电子签章授权书》", "h5/contract/authorization.html"),
    LOAN_CONTRACT(2, "《借款协议》", "h5/contract/borrowTerm.html"),
    EXTEND_CONTRACT(3, "《展期协议》", "h5/contract/extendTerm.html");

    SaasContractEnum(Integer code, String msg, String url) {
        this.code = code;
        this.msg = msg;
        this.url = url;
    }

    private Integer code;

    private String msg;

    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}