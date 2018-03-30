package com.beitu.saas.intergration.esign.param;

import java.io.Serializable;

/**
 * @author linanjun
 * @create 2018/3/29 下午5:24
 * @description
 */
public class PersonAccountParam implements Serializable {

    private String userCode;

    private String userName;

    private String identityCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }
}
