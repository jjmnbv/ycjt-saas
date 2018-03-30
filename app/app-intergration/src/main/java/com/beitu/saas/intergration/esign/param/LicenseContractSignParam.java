package com.beitu.saas.intergration.esign.param;

/**
 * @author linanjun
 * @create 2018/3/30 下午3:49
 * @description
 */
public class LicenseContractSignParam {

    private Long userId;

    private String userAccountId;

    private String userSealData;

    private String srcPdf;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserSealData() {
        return userSealData;
    }

    public void setUserSealData(String userSealData) {
        this.userSealData = userSealData;
    }

    public String getSrcPdf() {
        return srcPdf;
    }

    public void setSrcPdf(String srcPdf) {
        this.srcPdf = srcPdf;
    }

}
