package com.beitu.saas.sms.ro;

public class RuleRO {

    private String ruleName;

    private String fact;

    private String ruleCondiction;

    private String startSendTime;

    private String endSendTime;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getRuleCondiction() {
        return ruleCondiction;
    }

    public void setRuleCondiction(String ruleCondiction) {
        this.ruleCondiction = ruleCondiction;
    }

    public String getStartSendTime() {
        return startSendTime;
    }

    public void setStartSendTime(String startSendTime) {
        this.startSendTime = startSendTime;
    }

    public String getEndSendTime() {
        return endSendTime;
    }

    public void setEndSendTime(String endSendTime) {
        this.endSendTime = endSendTime;
    }
}
