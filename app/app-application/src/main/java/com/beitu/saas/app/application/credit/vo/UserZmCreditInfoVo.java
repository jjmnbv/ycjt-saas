package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午9:37
 * @description
 */
@ApiModel(value = "用户芝麻信用信息模块")
public class UserZmCreditInfoVo implements ResponseData {

    @ApiModelProperty(value = "芝麻分")
    private String zmScore;

    public String getZmScore() {
        return zmScore;
    }

    public void setZmScore(String zmScore) {
        this.zmScore = zmScore;
    }
}