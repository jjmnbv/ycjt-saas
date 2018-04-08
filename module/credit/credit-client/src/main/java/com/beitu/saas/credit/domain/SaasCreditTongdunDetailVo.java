package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditTongdunDetail;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.680
 */
public class SaasCreditTongdunDetailVo implements ResponseData, Serializable {

    private Long saasCreditTongdunDetailId;

    /**
     * 同盾信用记录查询表ID
     */
    private Long recordId;
    /**
     * 同盾流水编号
     */
    private String reportId;
    /**
     * 同盾报告
     */
    private String tongdunReport;
    /**
     * 同盾分
     */
    private String finalScore;
    /**
     * 同盾结论
     */
    private String finalDecision;
    /**
     * 报告条目
     */
    private String itemName;
    /**
     * 报告时间
     */
    private Date reportTime;

    private Date gmtCreate;

    public Long getSaasCreditTongdunDetailId() {
        return saasCreditTongdunDetailId;
    }

    public void setSaasCreditTongdunDetailId(Long saasCreditTongdunDetailId) {
        this.saasCreditTongdunDetailId = saasCreditTongdunDetailId;
    }


    public Long getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getReportId() {
        return this.reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getTongdunReport() {
        return this.tongdunReport;
    }

    public void setTongdunReport(String tongdunReport) {
        this.tongdunReport = tongdunReport;
    }

    public String getFinalScore() {
        return this.finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getFinalDecision() {
        return this.finalDecision;
    }

    public void setFinalDecision(String finalDecision) {
        this.finalDecision = finalDecision;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getReportTime() {
        return this.reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public static SaasCreditTongdunDetailVo convertEntityToVO(SaasCreditTongdunDetail saasCreditTongdunDetail) {
        if (saasCreditTongdunDetail == null) {
            return null;
        }
        SaasCreditTongdunDetailVo saasCreditTongdunDetailVo = new SaasCreditTongdunDetailVo();
        BeanUtils.copyProperties(saasCreditTongdunDetail, saasCreditTongdunDetailVo);
        saasCreditTongdunDetailVo.setSaasCreditTongdunDetailId(saasCreditTongdunDetail.getId());
        return saasCreditTongdunDetailVo;
    }

    public static SaasCreditTongdunDetail convertVOToEntity(SaasCreditTongdunDetailVo saasCreditTongdunDetailVo) {
        if (saasCreditTongdunDetailVo == null) {
            return null;
        }
        SaasCreditTongdunDetail saasCreditTongdunDetail = new SaasCreditTongdunDetail();
        BeanUtils.copyProperties(saasCreditTongdunDetailVo, saasCreditTongdunDetail);
        saasCreditTongdunDetail.setId(saasCreditTongdunDetailVo.getSaasCreditTongdunDetailId());
        return saasCreditTongdunDetail;
    }

}
