package com.beitu.saas.risk.domain.platform.zmxy.rong;

import com.alibaba.fastjson.annotation.JSONField;
import com.beitu.saas.risk.domain.platform.zmxy.rong.info.ZmxyWatchListDetails;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-06-12 下午8:20
 */


public class ZmxyWatchList implements Serializable{

    private Boolean success;
    @JSONField(name = "biz_no")
    private String  bizNo;
    private List<ZmxyWatchListDetails>   details;
    @JSONField(name = "is_matched")
    private Boolean matched;

    public Boolean getSuccess() {
        return success;
    }

    public ZmxyWatchList setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getBizNo() {
        return bizNo;
    }

    public ZmxyWatchList setBizNo(String bizNo) {
        this.bizNo = bizNo;
        return this;
    }

    public List<ZmxyWatchListDetails> getDetails() {
        return details;
    }

    public ZmxyWatchList setDetails(List<ZmxyWatchListDetails> details) {
        this.details = details;
        return this;
    }

    public Boolean getMatched() {
        return matched;
    }

    public ZmxyWatchList setMatched(Boolean matched) {
        this.matched = matched;
        return this;
    }
}
