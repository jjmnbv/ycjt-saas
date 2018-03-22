package com.beitu.saas.risk.domain.platform.zmxy.watch;


import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.platform.zmxy.data.IvsDetail;
import com.beitu.saas.risk.domain.platform.zmxy.data.ZmWatchListDetail;

import java.util.List;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 15:54
 */
public class TripleZmxyIvsWatchOutput extends TripleServiceBaseOutput {

    private String ivsBizNo;
    private Integer ivsScore;
    //在关注名单中   true=命中 false=未命中
    private String watchBizNo;
    private Boolean watchMatched;
    //对各项输入参数校验结果的风险因素code与风险描述说明
    private List<IvsDetail> ivsDetails;
    //行业关注名单信息列表
    private List<ZmWatchListDetail> watchDetails;

    public String getIvsBizNo() {
        return ivsBizNo;
    }

    public TripleZmxyIvsWatchOutput setIvsBizNo(String ivsBizNo) {
        this.ivsBizNo = ivsBizNo;
        return this;
    }

    public Integer getIvsScore() {
        return ivsScore;
    }

    public TripleZmxyIvsWatchOutput setIvsScore(Integer ivsScore) {
        this.ivsScore = ivsScore;
        return this;
    }

    public String getWatchBizNo() {
        return watchBizNo;
    }

    public TripleZmxyIvsWatchOutput setWatchBizNo(String watchBizNo) {
        this.watchBizNo = watchBizNo;
        return this;
    }

    public Boolean getWatchMatched() {
        return watchMatched;
    }

    public TripleZmxyIvsWatchOutput setWatchMatched(Boolean watchMatched) {
        this.watchMatched = watchMatched;
        return this;
    }

    public List<IvsDetail> getIvsDetails() {
        return ivsDetails;
    }

    public TripleZmxyIvsWatchOutput setIvsDetails(List<IvsDetail> ivsDetails) {
        this.ivsDetails = ivsDetails;
        return this;
    }

    public List<ZmWatchListDetail> getWatchDetails() {
        return watchDetails;
    }

    public TripleZmxyIvsWatchOutput setWatchDetails(List<ZmWatchListDetail> watchDetails) {
        this.watchDetails = watchDetails;
        return this;
    }
}
