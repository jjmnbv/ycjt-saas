package com.beitu.saas.risk.domain.platform.zmxy.antiFraud;

import com.beitu.saas.risk.domain.platform.zmxy.data.DataBase;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2017-04-06 13:13
 */
public class AntiFraudVerify extends DataBase implements Serializable {
    @JsonProperty(value = "verify_code")
    private List<String> verifyCodeList;

    public List<String> getVerifyCodeList() {
        return verifyCodeList;
    }

    public AntiFraudVerify setVerifyCodeList(List<String> verifyCodeList) {
        this.verifyCodeList = verifyCodeList;
        return this;
    }
}
