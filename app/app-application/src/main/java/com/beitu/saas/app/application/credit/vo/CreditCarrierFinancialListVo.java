package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.credit.domain.SaasCreditCarrierRecordVo;
import com.fqgj.common.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by linchengyu on 17/6/27.
 */
public class CreditCarrierFinancialListVo implements Serializable {

    /**
     * 类型
     */
    private String typeName;

    /**
     * 通话次数
     */
    private String callTime;

    /**
     * 通话时长
     */
    private String duration;

    /**
     * 最近通话
     */
    private String lastTime;

    public CreditCarrierFinancialListVo(SaasCreditCarrierRecordVo saasCreditCarrierRecordVo) {
        if (saasCreditCarrierRecordVo != null) {
            this.typeName = saasCreditCarrierRecordVo.getMerchant();
            this.callTime = (saasCreditCarrierRecordVo.getCallingTime() + saasCreditCarrierRecordVo.getCalledTime()) + "";
            this.duration = (saasCreditCarrierRecordVo.getTotalDuration() / 60 + ((saasCreditCarrierRecordVo.getCallingDuration() % 60 > 0) ? 1 : 0)) + "分";
            Date lastTime = DateUtil.getDate(saasCreditCarrierRecordVo.getLocation(), "yyyy-MM-dd HH:mm:ss");
            if (lastTime != null) {
                this.lastTime = DateUtil.getDate(DateUtil.getDate(saasCreditCarrierRecordVo.getLocation(), "yyyy-MM-dd HH:mm:ss"), "yy-MM-dd HH:mm");
            } else {
                this.lastTime = saasCreditCarrierRecordVo.getLocation();
            }
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
