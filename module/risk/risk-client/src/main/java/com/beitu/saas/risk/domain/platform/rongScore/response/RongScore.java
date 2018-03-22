package com.beitu.saas.risk.domain.platform.rongScore.response;

import java.io.Serializable;

/**
 * Created by liupengpeng on 2017/9/23.
 */
public class RongScore implements Serializable {
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public RongScore setScore(Integer score) {
        this.score = score;
        return this;
    }
}
