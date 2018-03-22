package com.beitu.saas.risk.domain.platform.zmxy.antiFraud;


import com.beitu.saas.risk.domain.platform.zmxy.data.DataBase;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2017-04-06 13:13
 */
public class AntiFraudApply extends DataBase implements Serializable {
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public AntiFraudApply setScore(Integer score) {
        this.score = score;
        return this;
    }
}
