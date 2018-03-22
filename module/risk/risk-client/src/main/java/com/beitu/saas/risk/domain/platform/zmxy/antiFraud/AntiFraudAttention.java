package com.beitu.saas.risk.domain.platform.zmxy.antiFraud;

import com.beitu.saas.risk.domain.platform.zmxy.data.DataBase;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2017-04-06 13:13
 */
public class AntiFraudAttention extends DataBase implements Serializable {
    @JsonProperty(value = "risk_code")
    private List<String> riskCodeList;
    private String hit;

    public List<String> getRiskCodeList() {
        return riskCodeList;
    }

    public AntiFraudAttention setRiskCodeList(List<String> riskCodeList) {
        this.riskCodeList = riskCodeList;
        return this;
    }

    public String getHit() {
        return hit;
    }

    public AntiFraudAttention setHit(String hit) {
        this.hit = hit;
        return this;
    }
}
