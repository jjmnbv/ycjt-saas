package com.beitu.saas.risk.domain.platform.zmxy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 22:36
 */
public class ZmxyIvs extends DataBase implements Serializable {
    @JsonProperty(value = "ivs_score")
    private Integer ivsScore;
    @JsonProperty(value = "ivs_detail")
    private List<IvsDetail> ivsDetail;

    public Integer getIvsScore() {
        return ivsScore;
    }

    public void setIvsScore(Integer ivsScore) {
        this.ivsScore = ivsScore;
    }

    public List<IvsDetail> getIvsDetail() {
        return ivsDetail;
    }

    public void setIvsDetail(List<IvsDetail> ivsDetail) {
        this.ivsDetail = ivsDetail;
    }
}
