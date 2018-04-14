package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerEmergentContact;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.870
 */
public class SaasBorrowerEmergentContactVo implements ResponseData, Serializable {

    private Long saasBorrowerEmergentContactId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 直系亲属联系人类型
     */
    private String familyType;
    /**
     * 直系亲属联系人输入姓名
     */
    private String familyName;
    /**
     * 直系亲属联系人手机号
     */
    private String familyMobile;
    /**
     * 同事朋友联系人类型
     */
    private String friendType;
    /**
     * 同事朋友联系人输入姓名
     */
    private String friendName;
    /**
     * 同事朋友联系人手机号
     */
    private String friendMobile;

    public Long getSaasBorrowerEmergentContactId() {
        return saasBorrowerEmergentContactId;
    }

    public void setSaasBorrowerEmergentContactId(Long saasBorrowerEmergentContactId) {
        this.saasBorrowerEmergentContactId = saasBorrowerEmergentContactId;
    }

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getFamilyType() {
        return familyType;
    }

    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyMobile() {
        return familyMobile;
    }

    public void setFamilyMobile(String familyMobile) {
        this.familyMobile = familyMobile;
    }

    public String getFriendType() {
        return friendType;
    }

    public void setFriendType(String friendType) {
        this.friendType = friendType;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendMobile() {
        return friendMobile;
    }

    public void setFriendMobile(String friendMobile) {
        this.friendMobile = friendMobile;
    }

    public static SaasBorrowerEmergentContactVo convertEntityToVO(SaasBorrowerEmergentContact saasBorrowerEmergentContact) {
        if (saasBorrowerEmergentContact == null) {
            return null;
        }
        SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo = new SaasBorrowerEmergentContactVo();
        BeanUtils.copyProperties(saasBorrowerEmergentContact, saasBorrowerEmergentContactVo);
        saasBorrowerEmergentContactVo.setSaasBorrowerEmergentContactId(saasBorrowerEmergentContact.getId());
        return saasBorrowerEmergentContactVo;
    }

    public static SaasBorrowerEmergentContact convertVOToEntity(SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo) {
        if (saasBorrowerEmergentContactVo == null) {
            return null;
        }
        SaasBorrowerEmergentContact saasBorrowerEmergentContact = new SaasBorrowerEmergentContact();
        BeanUtils.copyProperties(saasBorrowerEmergentContactVo, saasBorrowerEmergentContact);
        saasBorrowerEmergentContact.setId(saasBorrowerEmergentContactVo.getSaasBorrowerEmergentContactId());
        return saasBorrowerEmergentContact;
    }

}
