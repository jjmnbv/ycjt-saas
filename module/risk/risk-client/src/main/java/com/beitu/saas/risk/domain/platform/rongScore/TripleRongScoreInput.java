package com.beitu.saas.risk.domain.platform.rongScore;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-11-16 下午2:21
 */


public class TripleRongScoreInput extends TripleServiceBaseInput {

    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public TripleRongScoreInput setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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
