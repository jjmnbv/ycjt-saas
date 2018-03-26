package com.beitu.saas.rest.controller.channel.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:09
 */
@ApiModel(description = "新增/编辑渠道")
public class SaasChannelRequestParam extends ParamsObject {

    @NotBlank(message = "操作类型不能为空")
    @ApiModelProperty(value = "操作类型: 0-新增 1-编辑", required = true)
    private Integer opType;

    @ApiModelProperty(value = "渠道号")
    private String channelCode;

    @NotBlank(message = "机构号不能为空")
    @ApiModelProperty(value = "机构号", required = true)
    private String merchantCode;

    @NotBlank(message = "渠道名称不能为空")
    @ApiModelProperty(value = "渠道号名称", required = true)
    private String channelName;

    @NotBlank(message = "负责人不能为空")
    @ApiModelProperty(value = "机负责人", required = true)
    private String chargePerson;

    /**
     * 备注
     */
    private String remark;

    /**
     * 风控模块选项
     */
    private List<SaasModuleRequestParam> saasModuleRequestParams;

    public String getChannelCode() {
        return channelCode;
    }

    public SaasChannelRequestParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public Integer getOpType() {
        return opType;
    }

    public SaasChannelRequestParam setOpType(Integer opType) {
        this.opType = opType;
        return this;
    }

    /**
     * 风控模块具体字段
     */
    private List<SaasModuleItemRequestParam> saasModuleItemRequestParams;

    public String getMerchantCode() {
        return merchantCode;
    }

    public SaasChannelRequestParam setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasChannelRequestParam setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public SaasChannelRequestParam setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public SaasChannelRequestParam setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public List<SaasModuleRequestParam> getSaasModuleRequestParams() {
        return saasModuleRequestParams;
    }

    public SaasChannelRequestParam setSaasModuleRequestParams(List<SaasModuleRequestParam> saasModuleRequestParams) {
        this.saasModuleRequestParams = saasModuleRequestParams;
        return this;
    }

    public List<SaasModuleItemRequestParam> getSaasModuleItemRequestParams() {
        return saasModuleItemRequestParams;
    }

    public SaasChannelRequestParam setSaasModuleItemRequestParams(List<SaasModuleItemRequestParam> saasModuleItemRequestParams) {
        this.saasModuleItemRequestParams = saasModuleItemRequestParams;
        return this;
    }

    @Override
    public void validate() {

    }
}
