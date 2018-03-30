package com.beitu.saas.app.application.contract.vo;


public class AuthorizationLawTermVo {

    private Long userId;

    private String userCode;

    private String signUrl;

    private String pdfPath;

    private String userName;

    private String identityNo;

    /**
     * 落款时间
     */
    private String inscribeDate;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getInscribeDate() {
        return inscribeDate;
    }

    public void setInscribeDate(String inscribeDate) {
        this.inscribeDate = inscribeDate;
    }
}
