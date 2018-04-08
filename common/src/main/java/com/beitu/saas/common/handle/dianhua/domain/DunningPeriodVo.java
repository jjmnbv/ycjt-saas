package com.beitu.saas.common.handle.dianhua.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by linchengyu on 17/8/1.
 */
public class DunningPeriodVo {
    
    private DunningStatisticVo dunning;
    
    @JsonProperty("not_sure_dunning")
    private DunningStatisticVo notSureDunning;
    
    public DunningStatisticVo getDunning() {
        return dunning;
    }
    
    public void setDunning(DunningStatisticVo dunning) {
        this.dunning = dunning;
    }
    
    public DunningStatisticVo getNotSureDunning() {
        return notSureDunning;
    }
    
    public void setNotSureDunning(DunningStatisticVo notSureDunning) {
        this.notSureDunning = notSureDunning;
    }
}
