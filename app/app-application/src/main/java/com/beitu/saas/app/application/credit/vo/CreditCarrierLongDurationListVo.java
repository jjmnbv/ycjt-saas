package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.common.utils.LocationUtil;
import com.beitu.saas.common.utils.StringUtil;
import com.beitu.saas.credit.domain.SaasCreditCarrierRecordVo;
import com.fqgj.common.utils.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linchengyu on 17/6/27.
 */
public class CreditCarrierLongDurationListVo implements Serializable {

    /**
     * 电话
     */
    private String mobile;

    /**
     * 归属地
     */
    private String location;

    /**
     * 呼入呼出
     */
    private String inAndOut;

    /**
     * 时长
     */
    private String duration;

    /**
     * 标签
     */
    private String tag;

    public CreditCarrierLongDurationListVo(SaasCreditCarrierRecordVo saasCreditCarrierRecordVo, List<CreditBmpMatchVo> creditBmpMatchVos) {
        this.mobile = saasCreditCarrierRecordVo.getPhone();
        this.location = LocationUtil.formatLocation(saasCreditCarrierRecordVo.getLocation());
        this.inAndOut = (saasCreditCarrierRecordVo.getCalledTime() == null ? 0 : saasCreditCarrierRecordVo.getCalledTime()) + "/" + (saasCreditCarrierRecordVo.getCallingTime() == null ? 0 : saasCreditCarrierRecordVo.getCallingTime());

        Integer totalDuration = ((saasCreditCarrierRecordVo.getCalledDuration() == null ? 0 : saasCreditCarrierRecordVo.getCalledDuration()) + (saasCreditCarrierRecordVo.getCallingDuration() == null ? 0 : saasCreditCarrierRecordVo.getCallingDuration()));
        this.duration = (totalDuration / 60 + ((totalDuration % 60 > 0) ? 1 : 0)) + "分";

        List<String> tagList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(creditBmpMatchVos)) {
            for (CreditBmpMatchVo creditBmpMatchVo : creditBmpMatchVos) {
                if (CollectionUtils.isNotEmpty(creditBmpMatchVo.getNumberList()) &&
                        creditBmpMatchVo.getNumberList().contains(saasCreditCarrierRecordVo.getPhone())) {
                    tagList.add(creditBmpMatchVo.getTypeName());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(tagList)) {
            this.tag = StringUtil.listToString(tagList, '/');
        }
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
