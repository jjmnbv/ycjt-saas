package com.beitu.saas.risk.domain.platform.zmxy.rong;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-06-13 下午5:16
 */


public class ZmxyScore {
    //芝麻信用得分
    private String score;

    public String getScore() {
        return score;
    }

    public ZmxyScore setScore(String score) {
        this.score = score;
        return this;
    }
}
