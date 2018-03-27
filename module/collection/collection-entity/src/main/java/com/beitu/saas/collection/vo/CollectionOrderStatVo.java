package com.beitu.saas.collection.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/27
 * Time: 下午12:02
 */
public class CollectionOrderStatVo {

    private String channelNo;
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

    public String getChannelNo() {
        return channelNo;
    }

    public CollectionOrderStatVo setChannelNo(String channelNo) {
        this.channelNo = channelNo;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public CollectionOrderStatVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public Integer getIntoPiecesNum() {
        return intoPiecesNum;
    }

    public CollectionOrderStatVo setIntoPiecesNum(Integer intoPiecesNum) {
        this.intoPiecesNum = intoPiecesNum;
        return this;
    }

    public Integer getPrimaryReviewerNum() {
        return primaryReviewerNum;
    }

    public CollectionOrderStatVo setPrimaryReviewerNum(Integer primaryReviewerNum) {
        this.primaryReviewerNum = primaryReviewerNum;
        return this;
    }

    public BigDecimal getPrimaryReviewerRatio() {
        return primaryReviewerRatio;
    }

    public CollectionOrderStatVo setPrimaryReviewerRatio(BigDecimal primaryReviewerRatio) {
        this.primaryReviewerRatio = primaryReviewerRatio;
        return this;
    }

    public Integer getFinalReviewerNum() {
        return finalReviewerNum;
    }

    public CollectionOrderStatVo setFinalReviewerNum(Integer finalReviewerNum) {
        this.finalReviewerNum = finalReviewerNum;
        return this;
    }

    public BigDecimal getFinalReviewerRatio() {
        return finalReviewerRatio;
    }

    public CollectionOrderStatVo setFinalReviewerRatio(BigDecimal finalReviewerRatio) {
        this.finalReviewerRatio = finalReviewerRatio;
        return this;
    }

    public BigDecimal getFinalReviewerBaseRatio() {
        return finalReviewerBaseRatio;
    }

    public CollectionOrderStatVo setFinalReviewerBaseRatio(BigDecimal finalReviewerBaseRatio) {
        this.finalReviewerBaseRatio = finalReviewerBaseRatio;
        return this;
    }

    public Integer getLoanLenderNum() {
        return loanLenderNum;
    }

    public CollectionOrderStatVo setLoanLenderNum(Integer loanLenderNum) {
        this.loanLenderNum = loanLenderNum;
        return this;
    }

    public BigDecimal getLoanLenderRatio() {
        return loanLenderRatio;
    }

    public CollectionOrderStatVo setLoanLenderRatio(BigDecimal loanLenderRatio) {
        this.loanLenderRatio = loanLenderRatio;
        return this;
    }

    public BigDecimal getLoanLenderBaseRatio() {
        return loanLenderBaseRatio;
    }

    public CollectionOrderStatVo setLoanLenderBaseRatio(BigDecimal loanLenderBaseRatio) {
        this.loanLenderBaseRatio = loanLenderBaseRatio;
        return this;
    }
}
