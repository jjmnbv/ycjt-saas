package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditCarrierRecord;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.658
 */
public class SaasCreditCarrierRecordVo implements ResponseData, Serializable {

    private Long saasCreditCarrierRecordId;

    /**
     * 运营商报告查询表ID
     */
    private Long recordId;
    /**
     * 号码
     */
    private String phone;
    /**
     * 商户名
     */
    private String merchant;
    /**
     * 地址
     */
    private String location;
    /**
     * 呼出次数
     */
    private Integer callingTime;
    /**
     * 呼入次数
     */
    private Integer calledTime;
    /**
     * 呼出时长（秒）
     */
    private Integer callingDuration;
    /**
     * 呼入时长（秒）
     */
    private Integer calledDuration;
    /**
     * 同通话时长（秒）
     */
    private Integer totalDuration;
    /**
     * 记录类型，1：高频联系人记录，2：商户通话记录，3：通话活跃记录
     */
    private Integer type;

    public Long getSaasCreditCarrierRecordId() {
        return saasCreditCarrierRecordId;
    }

    public void setSaasCreditCarrierRecordId(Long saasCreditCarrierRecordId) {
        this.saasCreditCarrierRecordId = saasCreditCarrierRecordId;
    }


    public Long getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMerchant() {
        return this.merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCallingTime() {
        return this.callingTime;
    }

    public void setCallingTime(Integer callingTime) {
        this.callingTime = callingTime;
    }

    public Integer getCalledTime() {
        return this.calledTime;
    }

    public void setCalledTime(Integer calledTime) {
        this.calledTime = calledTime;
    }

    public Integer getCallingDuration() {
        return this.callingDuration;
    }

    public void setCallingDuration(Integer callingDuration) {
        this.callingDuration = callingDuration;
    }

    public Integer getCalledDuration() {
        return this.calledDuration;
    }

    public void setCalledDuration(Integer calledDuration) {
        this.calledDuration = calledDuration;
    }

    public Integer getTotalDuration() {
        return this.totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static SaasCreditCarrierRecordVo convertEntityToVO(SaasCreditCarrierRecord saasCreditCarrierRecord) {
        if (saasCreditCarrierRecord == null) {
            return null;
        }
        SaasCreditCarrierRecordVo saasCreditCarrierRecordVo = new SaasCreditCarrierRecordVo();
        BeanUtils.copyProperties(saasCreditCarrierRecord, saasCreditCarrierRecordVo);
        saasCreditCarrierRecordVo.setSaasCreditCarrierRecordId(saasCreditCarrierRecord.getId());
        return saasCreditCarrierRecordVo;
    }

    public static SaasCreditCarrierRecord convertVOToEntity(SaasCreditCarrierRecordVo saasCreditCarrierRecordVo) {
        if (saasCreditCarrierRecordVo == null) {
            return null;
        }
        SaasCreditCarrierRecord saasCreditCarrierRecord = new SaasCreditCarrierRecord();
        BeanUtils.copyProperties(saasCreditCarrierRecordVo, saasCreditCarrierRecord);
        saasCreditCarrierRecord.setId(saasCreditCarrierRecordVo.getSaasCreditCarrierRecordId());
        return saasCreditCarrierRecord;
    }

}
