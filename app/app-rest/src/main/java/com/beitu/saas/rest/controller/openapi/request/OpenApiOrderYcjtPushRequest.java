package com.beitu.saas.rest.controller.openapi.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:09
 * @description
 */
@ApiModel(description = "借款人多平台信息查询")
public class OpenApiOrderYcjtPushRequest extends ParamsObject {
    
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    
    @ApiModelProperty(value = "身份证号", required = true)
    @NotBlank(message = "身份证号不能为空")
    private String identityNo;
    
    @ApiModelProperty(value = "芝麻分", required = true)
    @NotBlank(message = "芝麻分不能为空")
    private String zm;
    
    @ApiModelProperty(value = "订单数据", required = true)
    @NotBlank(message = "订单数据不能为空")
    private String data;
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getIdentityNo() {
        return identityNo;
    }
    
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
    
    public String getZm() {
        return zm;
    }
    
    public void setZm(String zm) {
        this.zm = zm;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    @Override
    public void validate() {
    
    }
    
}