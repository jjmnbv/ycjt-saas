package com.beitu.saas.rest.controller.saas.response;

import com.beitu.saas.borrower.domain.SaasBorrowerEmergentContactVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * @author linanjun
 * @create 2018/3/23 下午6:33
 * @description
 */
@ApiModel(value = "用户紧急联系人信息模块")
public class CreditEmergentContactResponse implements ResponseData {

    /**
     * 直系亲属联系人类型
     */
    @ApiModelProperty(value = "直系亲属")
    private String familyType;
    /**
     * 直系亲属联系人输入姓名
     */
    @ApiModelProperty(value = "直系亲属姓名")
    private String familyName;
    /**
     * 直系亲属联系人手机号
     */
    @ApiModelProperty(value = "直系亲属手机号码")
    private String familyMobile;
    /**
     * 同事朋友联系人类型
     */
    @ApiModelProperty(value = "同事朋友")
    private String friendType;
    /**
     * 同事朋友联系人输入姓名
     */
    @ApiModelProperty(value = "同事朋友姓名")
    private String friendName;
    /**
     * 同事朋友联系人手机号
     */
    @ApiModelProperty(value = "同事朋友手机号码")
    private String friendMobile;

    public CreditEmergentContactResponse(SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo) {
        if (saasBorrowerEmergentContactVo != null) {
            BeanUtils.copyProperties(saasBorrowerEmergentContactVo, this);
        }
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

}