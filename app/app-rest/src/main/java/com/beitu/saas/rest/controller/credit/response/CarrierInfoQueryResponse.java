package com.beitu.saas.rest.controller.credit.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/4/4 下午4:50
 * @description
 */
@ApiModel(value = "运营商信息")
public class CarrierInfoQueryResponse implements ResponseData {

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "身份证")
    private String idNumber;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "入网年份")
    private String opentime;

    @ApiModelProperty(value = "入网时间")
    private String netWorkTime;

    @ApiModelProperty(value = "套餐")
    private String priceplanname;

    @ApiModelProperty(value = "话费余额")
    private Float availablebalance = 0f;

    @ApiModelProperty(value = "活跃地区")
    private String activeRegion;

    @ApiModelProperty(value = "入网地区")
    private String registerRegion;

    @ApiModelProperty(value = "联系人最多地区")
    private String mostContactRegion;

    @ApiModelProperty(value = "互通联系人数量")
    private Integer interactionContactCnt = 0;

    @ApiModelProperty(value = "晚23点至早6点通话")
    private String nightCallInfo;


}