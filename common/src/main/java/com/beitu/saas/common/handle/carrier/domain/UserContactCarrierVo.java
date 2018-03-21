package com.beitu.saas.common.handle.carrier.domain;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2016-11-02 12:06
 */
public class UserContactCarrierVo implements Serializable {
    private static final long serialVersionUID = 5759759593970403199L;

    private Long userId;
    private Long borrowId;
    private String mobile;
    private String realName;
    private String identityNo;
    private String contactUrl;
    private String carrierUrl;
    private String mobileProvance;

    public String getMobileProvance() {
        return mobileProvance;
    }

    public UserContactCarrierVo setMobileProvance(String mobileProvance) {
        this.mobileProvance = mobileProvance;
        return this;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public UserContactCarrierVo setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserContactCarrierVo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public UserContactCarrierVo setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserContactCarrierVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserContactCarrierVo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public UserContactCarrierVo setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
        return this;
    }

    public String getCarrierUrl() {
        return carrierUrl;
    }

    public UserContactCarrierVo setCarrierUrl(String carrierUrl) {
        this.carrierUrl = carrierUrl;
        return this;
    }
}
