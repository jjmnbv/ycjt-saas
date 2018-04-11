package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;
import java.util.List;

public class OrderPushUserCarrierInfoVo implements Serializable {
    
    private Integer type;
    
    private String url;
    
    private OrderPushUserCarrierBaseVo base;
    
    private OrderPushUserCarrierExtVo ext;
    
    private List<OrderPushUserCarrierBillVo> bills;
    
    private List<OrderPushUserCarrierRecordVo> records;
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public OrderPushUserCarrierBaseVo getBase() {
        return base;
    }
    
    public void setBase(OrderPushUserCarrierBaseVo base) {
        this.base = base;
    }
    
    public OrderPushUserCarrierExtVo getExt() {
        return ext;
    }
    
    public void setExt(OrderPushUserCarrierExtVo ext) {
        this.ext = ext;
    }
    
    public List<OrderPushUserCarrierBillVo> getBills() {
        return bills;
    }
    
    public void setBills(List<OrderPushUserCarrierBillVo> bills) {
        this.bills = bills;
    }
    
    public List<OrderPushUserCarrierRecordVo> getRecords() {
        return records;
    }
    
    public void setRecords(List<OrderPushUserCarrierRecordVo> records) {
        this.records = records;
    }
}
