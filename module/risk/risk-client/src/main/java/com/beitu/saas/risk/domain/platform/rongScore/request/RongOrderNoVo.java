package com.beitu.saas.risk.domain.platform.rongScore.request;

import java.io.Serializable;

/**
 * Created by liupengpeng on 2017/9/23.
 */
public class RongOrderNoVo implements Serializable{
    /**
     * 第三方订单号
     */
    private String orderNo;
    private Long borrowId;
    private Long userId;

    public String getOrderNo() {
        return orderNo;
    }

    public RongOrderNoVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public RongOrderNoVo setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public RongOrderNoVo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
