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
@ApiModel(description = "新增渠道")
public class SaasChannelRequestVo extends ParamsObject {

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
    private List<SaasModuleRequestVo> saasModuleRequestVos;
    /**
     * 风控模块具体字段
     */
    private List<SaasModuleItemRequestVo> saasModuleItemRequestVos;

    public String getMerchantCode() {
        return merchantCode;
    }

    public SaasChannelRequestVo setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasChannelRequestVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public SaasChannelRequestVo setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public SaasChannelRequestVo setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public List<SaasModuleRequestVo> getSaasModuleRequestVos() {
        return saasModuleRequestVos;
    }

    public SaasChannelRequestVo setSaasModuleRequestVos(List<SaasModuleRequestVo> saasModuleRequestVos) {
        this.saasModuleRequestVos = saasModuleRequestVos;
        return this;
    }

    public List<SaasModuleItemRequestVo> getSaasModuleItemRequestVos() {
        return saasModuleItemRequestVos;
    }

    public SaasChannelRequestVo setSaasModuleItemRequestVos(List<SaasModuleItemRequestVo> saasModuleItemRequestVos) {
        this.saasModuleItemRequestVos = saasModuleItemRequestVos;
        return this;
    }

    @Override
    public void validate() {

    }
}
