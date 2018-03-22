package com.beitu.saas.risk.domain.platform.tongdun;


import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

/**
 * @Author 柳朋朋
 * @Create 2017-05-27 13:48
 */
public class TripleTongdunReportIdInput extends TripleServiceBaseInput {
    private String idCard;
    private String realName;
    private String mobile;

    public String getIdCard() {
        return idCard;
    }

    public TripleTongdunReportIdInput setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public TripleTongdunReportIdInput setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public TripleTongdunReportIdInput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public String getName() {
        return getRealName();
    }

    @Override
    public String getIdentityNo() {
        return getIdCard();
    }
}
