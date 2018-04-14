package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.credit.domain.SaasCreditCarrierBillVo;
import com.fqgj.common.utils.StrUtils;

import java.io.Serializable;

/**
 * Created by linchengyu on 17/6/27.
 */
public class CreditCarrierBillListVo implements Serializable {

    /**
     * 月份
     */
    private String time;

    /**
     * 月消费
     */
    private String fee;

    /**
     * 呼入呼出
     */
    private String inAndOut;

    /**
     * 时长
     */
    private String duration;

    public CreditCarrierBillListVo(SaasCreditCarrierBillVo saasCreditCarrierBillVo) {
        time = DateUtil.getDate(saasCreditCarrierBillVo.getBillDate(), "yy-MM");
        fee = saasCreditCarrierBillVo.getTotalFee() == null ? "未出" : (StrUtils.doubleDecimalStrValue(saasCreditCarrierBillVo.getTotalFee().doubleValue()) + "元");
        inAndOut = (saasCreditCarrierBillVo.getCalledTime() == null ? 0 : saasCreditCarrierBillVo.getCalledTime()) + "/" + (saasCreditCarrierBillVo.getCallingTime() == null ? 0 : saasCreditCarrierBillVo.getCallingTime());
        Integer totalDuration = ((saasCreditCarrierBillVo.getCalledDuration() == null ? 0 : saasCreditCarrierBillVo.getCalledDuration()) + (saasCreditCarrierBillVo.getCallingDuration() == null ? 0 : saasCreditCarrierBillVo.getCallingDuration()));
        duration = totalDuration + "分";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getInAndOut() {
        return inAndOut;
    }

    public void setInAndOut(String inAndOut) {
        this.inAndOut = inAndOut;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
