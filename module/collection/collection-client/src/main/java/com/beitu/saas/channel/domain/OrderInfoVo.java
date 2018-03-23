package com.beitu.saas.channel.domain;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/23
 * Time: 上午11:35
 */
public class OrderInfoVo {
    private String orderNo;
    private Integer status;

    public String getOrderNo() {
        return orderNo;
    }

    public OrderInfoVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderInfoVo setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
