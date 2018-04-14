package com.beitu.saas.common.handle.dianhua.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallVo;
import com.beitu.saas.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by linchengyu on 17/8/1.
 */
public class CarrierCallLogVo implements Serializable {
    
    private static final long serialVersionUID = 6722628349015795008L;
    
    @JSONField(name = "call_tel")
    private String callTel;
    
    @JSONField(name = "call_time")
    private Long callTime;
    
    @JSONField(name = "call_duration")
    private Integer callDuration;
    
    @JSONField(name = "call_method")
    private Integer callMethod;
    
    public CarrierCallLogVo(CarriersPhoneCallVo callVo) {
        if (callVo != null) {
            this.callTel = callVo.getPeernumber();
            this.callDuration = StringUtils.isEmpty(callVo.getCallTime()) ? 0 : Integer.valueOf(callVo.getCallTime());
            Date createtime = null;
            if (StringUtils.isNotEmpty(callVo.getCreatetime())) {
                createtime = DateUtil.getDate(callVo.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
            }
            if (createtime != null) {
                this.callTime = createtime.getTime() / 1000;
            }
            if (Objects.equals(callVo.getDialtype(), "1")) {
                this.callMethod = 1;
            } else if (Objects.equals(callVo.getDialtype(), "2")) {
                this.callMethod = 2;
            } else {
                this.callMethod = Objects.equals(callVo.getDialtype(), "主叫") ? 1 : 2;
            }

        }
    }
    
    public String getCallTel() {
        return callTel;
    }
    
    public void setCallTel(String callTel) {
        this.callTel = callTel;
    }
    
    public Long getCallTime() {
        return callTime;
    }
    
    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }
    
    public Integer getCallDuration() {
        return callDuration;
    }
    
    public void setCallDuration(Integer callDuration) {
        this.callDuration = callDuration;
    }
    
    public Integer getCallMethod() {
        return callMethod;
    }
    
    public void setCallMethod(Integer callMethod) {
        this.callMethod = callMethod;
    }
}
