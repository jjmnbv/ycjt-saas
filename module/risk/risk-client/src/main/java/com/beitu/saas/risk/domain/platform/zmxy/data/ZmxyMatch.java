package com.beitu.saas.risk.domain.platform.zmxy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 22:38
 */
public class ZmxyMatch extends DataBase implements Serializable {
    @JsonProperty(value = "is_matched")
    private Boolean isMatched;
    @JsonProperty(value = "details")
    private List<ZmWatchListDetail> details;

    public Boolean getMatched() {
        return isMatched;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }

    public List<ZmWatchListDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ZmWatchListDetail> details) {
        this.details = details;
    }
}
