package com.beitu.saas.common.handle.dianhua.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linchengyu on 17/8/1.
 */
public class DunningBodyVo implements Serializable {
    
    private static final long serialVersionUID = -2423197089137323834L;
    
    private String tel;
    
    private Long time;
    
    @JSONField(name = "call_log")
    private List<CarrierCallLogVo> callLong;
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public Long getTime() {
        return time;
    }
    
    public void setTime(Long time) {
        this.time = time;
    }
    
    public List<CarrierCallLogVo> getCallLong() {
        return callLong;
    }
    
    public void setCallLong(List<CarrierCallLogVo> callLong) {
        this.callLong = callLong;
    }
}
