package com.beitu.saas.risk.domain.platform.rongScore;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.beitu.saas.risk.domain.platform.rongScore.response.RongScore;

/**
 * Created by liupengpeng on 2017/9/23.
 */
public class TripleRongScoreOutput extends TripleServiceBaseOutput {
    @JsonProperty(value = "error")
    private String code;

    private String msg;

    @JsonProperty(value = "tianji_api_taojinyunreport_customscore_response")
    private RongScore rongScore;

    public String getCode() {
        return code;
    }

    public TripleRongScoreOutput setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public TripleRongScoreOutput setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public RongScore getRongScore() {
        return rongScore;
    }

    public TripleRongScoreOutput setRongScore(RongScore rongScore) {
        this.rongScore = rongScore;
        return this;
    }
}
