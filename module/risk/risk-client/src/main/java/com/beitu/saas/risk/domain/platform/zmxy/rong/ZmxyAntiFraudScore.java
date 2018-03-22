package com.beitu.saas.risk.domain.platform.zmxy.rong;

import java.io.Serializable;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-06-12 下午8:50
 */


public class ZmxyAntiFraudScore implements Serializable{
    /**
     * 反欺诈得分
     */
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public ZmxyAntiFraudScore setScore(Integer score) {
        this.score = score;
        return this;
    }
}
