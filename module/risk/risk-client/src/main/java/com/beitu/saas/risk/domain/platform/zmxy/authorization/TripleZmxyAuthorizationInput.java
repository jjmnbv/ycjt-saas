package com.beitu.saas.risk.domain.platform.zmxy.authorization;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class TripleZmxyAuthorizationInput extends TripleServiceBaseInput {
    private String idCard;
    private String realName;

    public String getIdCard() {
        return idCard;
    }

    public TripleZmxyAuthorizationInput setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public TripleZmxyAuthorizationInput setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    @Override
    public String getName() {
        return getRealName();
    }

    @Override
    public String getMobile() {
        return "";
    }

    @Override
    public String getIdentityNo() {
        return getIdCard();
    }
}
