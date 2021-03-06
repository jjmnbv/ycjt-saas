package com.beitu.saas.rest.controller.h5.request;

import com.beitu.saas.app.enums.VerifyCodeErrorCodeEnum;
import com.beitu.saas.common.utils.MobileUtil;
import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/22 下午2:12
 * @description
 */
@ApiModel(description = "保存风控模块紧急联系人信息")
public class CreditSaveEmergentContactRequest extends ParamsObject {

    /**
     * 直系亲属联系人类型
     */
    @ApiModelProperty(value = "直系亲属")
    @NotBlank(message = "直系亲属关系不能为空")
    private String familyType;
    /**
     * 直系亲属联系人输入姓名
     */
    @ApiModelProperty(value = "直系亲属姓名")
    @NotBlank(message = "直系亲属姓名不能为空")
    private String familyName;
    /**
     * 直系亲属联系人手机号
     */
    @ApiModelProperty(value = "直系亲属手机号码")
    @NotBlank(message = "直系亲属手机号码不能为空")
    private String familyMobile;
    /**
     * 同事朋友联系人类型
     */
    @ApiModelProperty(value = "同事朋友关系")
    @NotBlank(message = "同事朋友关系不能为空")
    private String friendType;
    /**
     * 同事朋友联系人输入姓名
     */
    @ApiModelProperty(value = "同事朋友姓名")
    @NotBlank(message = "同事朋友姓名不能为空")
    private String friendName;
    /**
     * 同事朋友联系人手机号
     */
    @ApiModelProperty(value = "同事朋友手机号码")
    @NotBlank(message = "同事朋友手机号码不能为空")
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

    @Override
    public void validate() {
        if (!MobileUtil.isMobile(familyMobile)) {
            throw new ApiIllegalArgumentException(VerifyCodeErrorCodeEnum.NOT_MOBILE);
        }
        if (!MobileUtil.isMobile(friendMobile)) {
            throw new ApiIllegalArgumentException(VerifyCodeErrorCodeEnum.NOT_MOBILE);
        }
        if (familyMobile.equals(friendMobile)) {
            throw new ApiIllegalArgumentException("亲属和朋友手机号不能相同");
        }
    }

}