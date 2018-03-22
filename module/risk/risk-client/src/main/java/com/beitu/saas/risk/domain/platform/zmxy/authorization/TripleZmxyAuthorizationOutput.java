package com.beitu.saas.risk.domain.platform.zmxy.authorization;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

public class TripleZmxyAuthorizationOutput extends TripleServiceBaseOutput {
    private Boolean flag;

    public Boolean getFlag() {
        return flag;
    }

    public TripleZmxyAuthorizationOutput setFlag(Boolean flag) {
        this.flag = flag;
        return this;
    }
}
