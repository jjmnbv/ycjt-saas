package com.beitu.saas.common.handle.dianhua.domain;

/**
 * Created by linchengyu on 17/8/1.
 */
public class DunningResultVo {
    
    private Integer status;
    
    private String msg;
    
    private DunningDataVo data;
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public DunningDataVo getData() {
        return data;
    }
    
    public void setData(DunningDataVo data) {
        this.data = data;
    }
}
