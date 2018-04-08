package com.beitu.saas.common.handle.dianhua.domain;

import java.util.Map;

/**
 * Created by linchengyu on 17/8/3.
 */
public class BmpTelResultVo {
    
    private String sid;
    
    private Map<String, Object> tags;
    
    public String getSid() {
        return sid;
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public Map<String, Object> getTags() {
        return tags;
    }
    
    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }
    
}
