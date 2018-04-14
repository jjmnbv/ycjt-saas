package com.beitu.saas.risk.domain.platform.zmxy.score;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-11-15 下午8:18
 */


public class TripleZmxyScoreInput extends TripleServiceBaseInput {
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public TripleZmxyScoreInput setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getMobile() {
        return "";
    }

    @Override
    public String getIdentityNo() {
        return "";
    }
}
