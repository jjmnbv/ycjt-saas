package com.beitu.saas.risk.domain.platform.zmxy.rong.info;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-06-12 下午8:27
 */


public class ZmxyWatchListDetails implements Serializable {
    @JSONField(name = "biz_code")
    private String bizCode;
    private String code;
    @JSONField(name = "extend_info")
    private List<ZmxyDetailsExtendInfo> extendInfo;
    private Integer level;
    @JSONField(name = "refresh_time")
    private String refreshTime;
    private boolean settlement;
    private String type;

    public String getBizCode() {
        return bizCode;
    }

    public ZmxyWatchListDetails setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ZmxyWatchListDetails setCode(String code) {
        this.code = code;
        return this;
    }

    public List<ZmxyDetailsExtendInfo> getExtendInfo() {
        return extendInfo;
    }

    public ZmxyWatchListDetails setExtendInfo(List<ZmxyDetailsExtendInfo> extendInfo) {
        this.extendInfo = extendInfo;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public ZmxyWatchListDetails setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public ZmxyWatchListDetails setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
        return this;
    }

    public boolean isSettlement() {
        return settlement;
    }

    public ZmxyWatchListDetails setSettlement(boolean settlement) {
        this.settlement = settlement;
        return this;
    }

    public String getType() {
        return type;
    }

    public ZmxyWatchListDetails setType(String type) {
        this.type = type;
        return this;
    }
}
