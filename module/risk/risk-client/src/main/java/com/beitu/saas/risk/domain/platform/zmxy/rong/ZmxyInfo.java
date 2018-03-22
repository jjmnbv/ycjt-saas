package com.beitu.saas.risk.domain.platform.zmxy.rong;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2017-04-28 14:53
 */
public class ZmxyInfo implements Serializable {
    private Long userId;

    private Long borrowId;

    private Integer zmxyScore;

    private String antiFraudInfo;

    public Long getUserId() {
        return userId;
    }

    public ZmxyInfo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public ZmxyInfo setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
        return this;
    }

    public Integer getZmxyScore() {
        return zmxyScore;
    }

    public ZmxyInfo setZmxyScore(Integer zmxyScore) {
        this.zmxyScore = zmxyScore;
        return this;
    }

    public String getAntiFraudInfo() {
        return antiFraudInfo;
    }

    public ZmxyInfo setAntiFraudInfo(String antiFraudInfo) {
        this.antiFraudInfo = antiFraudInfo;
        return this;
    }
}
