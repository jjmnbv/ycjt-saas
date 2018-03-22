package com.beitu.saas.risk.domain.platform.zmxy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 22:24
 */
public class ZmWatchListDetail implements Serializable {
    //风险信息行业编码
    @JsonProperty(value = "biz_code")
    private String bizCode;
    //行业名单风险类型
    private String type;
    //风险编码
    private String code;
    //数据刷新时间
    @JsonProperty(value = "refresh_time")
    private String refreshTime;
    //结清状态
    private Boolean settlement;
    //用户本人对该条负面记录有异议时，表示该异议处理流程的状态
    private String status;
    //	当用户进行异议处理，并核查完毕之后，仍有异议时，给出的声明
    private String statement;
    //	当用户进行异议处理，并核查完毕之后，仍有异议时，给出的声明
    private Integer level;
    //扩展信息
    @JsonProperty(value = "extend_info")
    List<ZmWatchListExtendInfo> extendInfo;

    //===============扩展信息===================
    private String currentOverdueStatus;

    public String getCurrentOverdueStatus() {
        return currentOverdueStatus;
    }

    public void setCurrentOverdueStatus(String currentOverdueStatus) {
        this.currentOverdueStatus = currentOverdueStatus;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    public Boolean getSettlement() {
        return settlement;
    }

    public void setSettlement(Boolean settlement) {
        this.settlement = settlement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<ZmWatchListExtendInfo> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(List<ZmWatchListExtendInfo> extendInfo) {
        this.extendInfo = extendInfo;
    }
}
