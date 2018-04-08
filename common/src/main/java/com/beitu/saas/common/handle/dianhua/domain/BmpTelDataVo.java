package com.beitu.saas.common.handle.dianhua.domain;

import java.util.List;

/**
 * Created by linchengyu on 17/8/3.
 */
public class BmpTelDataVo {
    
    private String sid;
    
    private List<BmpTelTagDataVo> tags;
    
    public BmpTelDataVo(String sid, List<BmpTelTagDataVo> tags) {
        this.sid = sid;
        this.tags = tags;
    }
    
    public String getSid() {
        return sid;
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public List<BmpTelTagDataVo> getTags() {
        return tags;
    }
    
    public void setTags(List<BmpTelTagDataVo> tags) {
        this.tags = tags;
    }
}
