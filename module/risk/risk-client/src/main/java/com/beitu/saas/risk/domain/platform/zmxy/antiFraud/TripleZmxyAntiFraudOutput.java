package com.beitu.saas.risk.domain.platform.zmxy.antiFraud;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2017-04-06 13:13
 */
public class TripleZmxyAntiFraudOutput extends TripleServiceBaseOutput {
    private List<String> verifyCodeList;
    private String verifyBizNo;
    private Integer applyScore;
    private String applyBizNo;
    private List<String> attentionCodeList;
    private String attentionHit;
    private String attentionBizNo;

    public List<String> getVerifyCodeList() {
        return verifyCodeList;
    }

    public TripleZmxyAntiFraudOutput setVerifyCodeList(List<String> verifyCodeList) {
        this.verifyCodeList = verifyCodeList;
        return this;
    }

    public String getVerifyBizNo() {
        return verifyBizNo;
    }

    public TripleZmxyAntiFraudOutput setVerifyBizNo(String verifyBizNo) {
        this.verifyBizNo = verifyBizNo;
        return this;
    }

    public Integer getApplyScore() {
        return applyScore;
    }

    public TripleZmxyAntiFraudOutput setApplyScore(Integer applyScore) {
        this.applyScore = applyScore;
        return this;
    }

    public String getApplyBizNo() {
        return applyBizNo;
    }

    public TripleZmxyAntiFraudOutput setApplyBizNo(String applyBizNo) {
        this.applyBizNo = applyBizNo;
        return this;
    }

    public List<String> getAttentionCodeList() {
        return attentionCodeList;
    }

    public TripleZmxyAntiFraudOutput setAttentionCodeList(List<String> attentionCodeList) {
        this.attentionCodeList = attentionCodeList;
        return this;
    }

    public String getAttentionHit() {
        return attentionHit;
    }

    public TripleZmxyAntiFraudOutput setAttentionHit(String attentionHit) {
        this.attentionHit = attentionHit;
        return this;
    }

    public String getAttentionBizNo() {
        return attentionBizNo;
    }

    public TripleZmxyAntiFraudOutput setAttentionBizNo(String attentionBizNo) {
        this.attentionBizNo = attentionBizNo;
        return this;
    }
}
