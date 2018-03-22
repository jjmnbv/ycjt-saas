package com.beitu.saas.risk.domain.platform.zmxy.feedback;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2016-12-23 11:38
 */
public class FeedBackData implements Serializable {
    @JSONField(name = "records")
    private List<FeedBackDetail> records;

    public List<FeedBackDetail> getRecords() {
        return records;
    }

    public FeedBackData setRecords(List<FeedBackDetail> records) {
        this.records = records;
        return this;
    }
}
