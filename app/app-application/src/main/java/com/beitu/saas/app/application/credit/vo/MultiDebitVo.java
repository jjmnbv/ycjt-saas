package com.beitu.saas.app.application.credit.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/17
 * Time: 下午4:02
 */
public class MultiDebitVo {
    /**
     *借款人CODE
     */
    private String borrowerCode;
    /**
     *手机号
     */
    private String mobile;
    /**
     *用户身份证号码
     */
    private String identityCode;
    /**
     *客户风险评分
     */
    private Integer score;
    /**
     *贷款失败率
     */
    private BigDecimal failRate;
    /**
     *多头数据存储地址
     */
    private String url;
    /**
     *失效日期
     */
    private Date expiredDt;
    /**
     *渠道状态(0-有效,1-失效)
     */
    private Integer status;

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public MultiDebitVo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public MultiDebitVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public MultiDebitVo setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public MultiDebitVo setScore(Integer score) {
        this.score = score;
        return this;
    }

    public BigDecimal getFailRate() {
        return failRate;
    }

    public MultiDebitVo setFailRate(BigDecimal failRate) {
        this.failRate = failRate;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MultiDebitVo setUrl(String url) {
        this.url = url;
        return this;
    }

    public Date getExpiredDt() {
        return expiredDt;
    }

    public MultiDebitVo setExpiredDt(Date expiredDt) {
        this.expiredDt = expiredDt;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public MultiDebitVo setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
