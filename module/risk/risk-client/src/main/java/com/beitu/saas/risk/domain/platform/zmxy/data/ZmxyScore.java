package com.beitu.saas.risk.domain.platform.zmxy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 22:33
 */
public class ZmxyScore extends DataBase implements Serializable {
    @JsonProperty(value = "zm_score")
    private String zmScore;

    public String getZmScore() {
        return zmScore;
    }

    public void setZmScore(String zmScore) {
        this.zmScore = zmScore;
    }
}
