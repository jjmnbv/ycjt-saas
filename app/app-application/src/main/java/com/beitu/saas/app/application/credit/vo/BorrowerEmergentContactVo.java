package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午9:29
 * @description
 */
@ApiModel(value = "用户紧急联系人信息模块")
public class BorrowerEmergentContactVo implements ResponseData {

    @ApiModelProperty(value = "直系亲属")
    private String familyType;

    @ApiModelProperty(value = "直系亲属姓名")
    private String familyName;

    @ApiModelProperty(value = "直系亲属手机号码")
    private String familyMobile;

    @ApiModelProperty(value = "同事朋友")
    private String friendType;

    @ApiModelProperty(value = "同事朋友姓名")
    private String friendName;

    @ApiModelProperty(value = "同事朋友手机号码")
    private String friendMobile;

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
