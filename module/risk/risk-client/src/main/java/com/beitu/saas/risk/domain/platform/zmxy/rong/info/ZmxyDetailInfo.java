package com.beitu.saas.risk.domain.platform.zmxy.rong.info;


import com.alibaba.fastjson.annotation.JSONField;
import com.beitu.saas.risk.domain.platform.zmxy.rong.*;

import java.io.Serializable;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-06-12 下午8:17
 */


public class ZmxyDetailInfo implements Serializable{
    @JSONField(name = "ali_trust_score")
    private ZmxyScore zmxyScore;

    @JSONField(name = "ali_trust_watchlist")
    private ZmxyWatchList zmxyWatchList;

    @JSONField(name = "zhi_cheate_score")
    private ZmxyAntiFraudScore zmxyAntiFraudScore;

    @JSONField(name = "zhi_cheate_list")
    private ZmxyAntiFraudWatchList zmxyAntiFraudWatchList;

    @JSONField(name = "zhi_cheate_verify")
    private ZmxyAntiFraudVeriy zmxyAntiFraudVeriy;


    public ZmxyScore getZmxyScore() {
        return zmxyScore;
    }

    public ZmxyDetailInfo setZmxyScore(ZmxyScore zmxyScore) {
        this.zmxyScore = zmxyScore;
        return this;
    }

    public ZmxyWatchList getZmxyWatchList() {
        return zmxyWatchList;
    }

    public ZmxyDetailInfo setZmxyWatchList(ZmxyWatchList zmxyWatchList) {
        this.zmxyWatchList = zmxyWatchList;
        return this;
    }

    public ZmxyAntiFraudScore getZmxyAntiFraudScore() {
        return zmxyAntiFraudScore;
    }

    public ZmxyDetailInfo setZmxyAntiFraudScore(ZmxyAntiFraudScore zmxyAntiFraudScore) {
        this.zmxyAntiFraudScore = zmxyAntiFraudScore;
        return this;
    }

    public ZmxyAntiFraudWatchList getZmxyAntiFraudWatchList() {
        return zmxyAntiFraudWatchList;
    }

    public ZmxyDetailInfo setZmxyAntiFraudWatchList(ZmxyAntiFraudWatchList zmxyAntiFraudWatchList) {
        this.zmxyAntiFraudWatchList = zmxyAntiFraudWatchList;
        return this;
    }

    public ZmxyAntiFraudVeriy getZmxyAntiFraudVeriy() {
        return zmxyAntiFraudVeriy;
    }

    public ZmxyDetailInfo setZmxyAntiFraudVeriy(ZmxyAntiFraudVeriy zmxyAntiFraudVeriy) {
        this.zmxyAntiFraudVeriy = zmxyAntiFraudVeriy;
        return this;
    }
}
