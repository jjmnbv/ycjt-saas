package com.beitu.saas.rest.controller.collection.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/24
 * Time: 下午1:36
 */
@ApiModel(value = "催收列表查询条件")
public class CollectionOrderQueryRequestParam {

    @ApiModelProperty(value = "逾期类型: 1-今日到期,2-逾期三天,...,8-90天以上")
    private Integer overdueDaysType;

    @ApiModelProperty(value = "借款人手机号码")
    private String mobile;

    @ApiModelProperty(value = "借款人姓名")
    private String name;

    @ApiModelProperty(value = "借款人身份证号")
    private String identityCode;

    @ApiModelProperty(value = "渠道CODE")
    private String channelCode;

    public Integer getOverdueDaysType() {
        return overdueDaysType;
    }

    public CollectionOrderQueryRequestParam setOverdueDaysType(Integer overdueDaysType) {
        this.overdueDaysType = overdueDaysType;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CollectionOrderQueryRequestParam setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionOrderQueryRequestParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public CollectionOrderQueryRequestParam setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public CollectionOrderQueryRequestParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }
}
