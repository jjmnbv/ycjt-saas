package com.beitu.saas.rest.controller.contract.response;

/**
 * @author linanjun
 * @create 2018/4/13 下午4:20
 * @description
 */
public class SealDataVo {

    /**
     * 出借人合同姓名
     */
    private String lenderUserName;
    /**
     * 出借人合同号
     */
    private String lenderIdentityNo;
    /**
     * 是否显示 出借人印章
     */
    private Boolean lenderStamp;
    /**
     * 出借人印章URL
     */
    private String lenderStampUrl;

    /**
     * 借款人姓名
     */
    private String borrowUserName;
    /**
     * 借款人身份证号
     */
    private String borrowIdentityNo;
    /**
     * 是否显示 借款人印章
     */
    private Boolean borrowStamp;
    /**
     * 借款人印章URL
     */
    private String borrowStampUrl;
    /**
     * 落款时间
     */
    private String inscribeDate;

    public String getLenderUserName() {
        return lenderUserName;
    }

    public void setLenderUserName(String lenderUserName) {
        this.lenderUserName = lenderUserName;
    }

    public String getLenderIdentityNo() {
        return lenderIdentityNo;
    }

    public void setLenderIdentityNo(String lenderIdentityNo) {
        this.lenderIdentityNo = lenderIdentityNo;
    }

    public Boolean getLenderStamp() {
        return lenderStamp;
    }

    public void setLenderStamp(Boolean lenderStamp) {
        this.lenderStamp = lenderStamp;
    }

    public String getLenderStampUrl() {
        return lenderStampUrl;
    }

    public void setLenderStampUrl(String lenderStampUrl) {
        this.lenderStampUrl = lenderStampUrl;
    }

    public String getBorrowUserName() {
        return borrowUserName;
    }

    public void setBorrowUserName(String borrowUserName) {
        this.borrowUserName = borrowUserName;
    }

    public String getBorrowIdentityNo() {
        return borrowIdentityNo;
    }

    public void setBorrowIdentityNo(String borrowIdentityNo) {
        this.borrowIdentityNo = borrowIdentityNo;
    }

    public Boolean getBorrowStamp() {
        return borrowStamp;
    }

    public void setBorrowStamp(Boolean borrowStamp) {
        this.borrowStamp = borrowStamp;
    }

    public String getBorrowStampUrl() {
        return borrowStampUrl;
    }

    public void setBorrowStampUrl(String borrowStampUrl) {
        this.borrowStampUrl = borrowStampUrl;
    }

    public String getInscribeDate() {
        return inscribeDate;
    }

    public void setInscribeDate(String inscribeDate) {
        this.inscribeDate = inscribeDate;
    }
}
