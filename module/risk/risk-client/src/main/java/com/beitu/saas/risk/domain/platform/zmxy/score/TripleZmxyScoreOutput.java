package com.beitu.saas.risk.domain.platform.zmxy.score;



import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 15:54
 */
public class TripleZmxyScoreOutput extends TripleServiceBaseOutput {

    private String openId;
    private Integer zmxyStatus;
    private String zmScoreBizNo;
    //用户的芝麻信用评分。分值范围[350,950]。如果用户数据不足，无法评分时，返回字符串"N/A"。
    private Integer zmScore;

    public Integer getZmxyStatus() {
        return zmxyStatus;
    }

    public TripleZmxyScoreOutput setZmxyStatus(Integer zmxyStatus) {
        this.zmxyStatus = zmxyStatus;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public TripleZmxyScoreOutput setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getZmScoreBizNo() {
        return zmScoreBizNo;
    }

    public TripleZmxyScoreOutput setZmScoreBizNo(String zmScoreBizNo) {
        this.zmScoreBizNo = zmScoreBizNo;
        return this;
    }

    public Integer getZmScore() {
        return zmScore;
    }

    public TripleZmxyScoreOutput setZmScore(Integer zmScore) {
        this.zmScore = zmScore;
        return this;
    }
}
