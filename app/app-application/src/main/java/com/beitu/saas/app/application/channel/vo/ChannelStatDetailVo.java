package com.beitu.saas.app.application.channel.vo;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/2
 * Time: 下午5:01
 */
@ApiModel(value = "渠道统计展示信息")
public class ChannelStatDetailVo {
    private String channelCode;
    private String channelName;
    private Integer intoPiecesNum;//进件数

    private Integer primaryReviewerNum;//初审通过数
    private BigDecimal primaryReviewerRatio;//初审通过数/进件数

    private Integer finalReviewerNum;//复审通过数
    private BigDecimal finalReviewerRatio;//复审通过数/初审通过数
    private BigDecimal finalReviewerBaseRatio;//复审通过数/进件数

    private Integer loanLenderNum;//放款数
    private BigDecimal loanLenderRatio;//放款通过数/复审通过数
    private BigDecimal loanLenderBaseRatio;//放款通过/进件数

    public String getChannelCode() {
        return channelCode;
    }

    public ChannelStatDetailVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public ChannelStatDetailVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public Integer getIntoPiecesNum() {
        return intoPiecesNum;
    }

    public ChannelStatDetailVo setIntoPiecesNum(Integer intoPiecesNum) {
        this.intoPiecesNum = intoPiecesNum;
        return this;
    }

    public Integer getPrimaryReviewerNum() {
        return primaryReviewerNum;
    }

    public ChannelStatDetailVo setPrimaryReviewerNum(Integer primaryReviewerNum) {
        this.primaryReviewerNum = primaryReviewerNum;
        return this;
    }

    public BigDecimal getPrimaryReviewerRatio() {
        return primaryReviewerRatio;
    }

    public ChannelStatDetailVo setPrimaryReviewerRatio(BigDecimal primaryReviewerRatio) {
        this.primaryReviewerRatio = primaryReviewerRatio;
        return this;
    }

    public Integer getFinalReviewerNum() {
        return finalReviewerNum;
    }

    public ChannelStatDetailVo setFinalReviewerNum(Integer finalReviewerNum) {
        this.finalReviewerNum = finalReviewerNum;
        return this;
    }

    public BigDecimal getFinalReviewerRatio() {
        return finalReviewerRatio;
    }

    public ChannelStatDetailVo setFinalReviewerRatio(BigDecimal finalReviewerRatio) {
        this.finalReviewerRatio = finalReviewerRatio;
        return this;
    }

    public BigDecimal getFinalReviewerBaseRatio() {
        return finalReviewerBaseRatio;
    }

    public ChannelStatDetailVo setFinalReviewerBaseRatio(BigDecimal finalReviewerBaseRatio) {
        this.finalReviewerBaseRatio = finalReviewerBaseRatio;
        return this;
    }

    public Integer getLoanLenderNum() {
        return loanLenderNum;
    }

    public ChannelStatDetailVo setLoanLenderNum(Integer loanLenderNum) {
        this.loanLenderNum = loanLenderNum;
        return this;
    }

    public BigDecimal getLoanLenderRatio() {
        return loanLenderRatio;
    }

    public ChannelStatDetailVo setLoanLenderRatio(BigDecimal loanLenderRatio) {
        this.loanLenderRatio = loanLenderRatio;
        return this;
    }

    public BigDecimal getLoanLenderBaseRatio() {
        return loanLenderBaseRatio;
    }

    public ChannelStatDetailVo setLoanLenderBaseRatio(BigDecimal loanLenderBaseRatio) {
        this.loanLenderBaseRatio = loanLenderBaseRatio;
        return this;
    }

    @Override
    public String toString() {
        return "ChannelStatDetailVo{" +
                "channelCode='" + channelCode + '\'' +
                ", channelName='" + channelName + '\'' +
                ", intoPiecesNum=" + intoPiecesNum +
                ", primaryReviewerNum=" + primaryReviewerNum +
                ", primaryReviewerRatio=" + primaryReviewerRatio +
                ", finalReviewerNum=" + finalReviewerNum +
                ", finalReviewerRatio=" + finalReviewerRatio +
                ", finalReviewerBaseRatio=" + finalReviewerBaseRatio +
                ", loanLenderNum=" + loanLenderNum +
                ", loanLenderRatio=" + loanLenderRatio +
                ", loanLenderBaseRatio=" + loanLenderBaseRatio +
                '}';
    }
}
