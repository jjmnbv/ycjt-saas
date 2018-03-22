package com.beitu.saas.risk.domain.platform.zmxy.rong;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-06-12 下午9:03
 */


public class ZmxyAntiFraudVeriy {

    @JSONField(name = "verify_code")
    private List<String> verifyCode;

    public List<String> getVerifyCode() {
        return verifyCode;
    }

    public ZmxyAntiFraudVeriy setVerifyCode(List<String> verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }
}
