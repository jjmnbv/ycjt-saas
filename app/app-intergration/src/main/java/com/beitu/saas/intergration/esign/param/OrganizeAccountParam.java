package com.beitu.saas.intergration.esign.param;

import com.timevale.esign.sdk.tech.impl.constants.OrganRegType;

/**
 * @author linanjun
 * @create 2018/3/29 下午5:24
 * @description
 */
public class OrganizeAccountParam {

    private String name;

    private String organizeCode;

    private String agentName;

    private String agentIdentityCode;

    private String legalName;

    private String legalIdentityCode;

    private OrganRegType organizeRegType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizeCode() {
        return organizeCode;
    }

    public void setOrganizeCode(String organizeCode) {
        this.organizeCode = organizeCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentIdentityCode() {
        return agentIdentityCode;
    }

    public void setAgentIdentityCode(String agentIdentityCode) {
        this.agentIdentityCode = agentIdentityCode;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalIdentityCode() {
        return legalIdentityCode;
    }

    public void setLegalIdentityCode(String legalIdentityCode) {
        this.legalIdentityCode = legalIdentityCode;
    }

    public OrganRegType getOrganizeRegType() {
        return organizeRegType;
    }

    public void setOrganizeRegType(OrganRegType organizeRegType) {
        this.organizeRegType = organizeRegType;
    }
}
