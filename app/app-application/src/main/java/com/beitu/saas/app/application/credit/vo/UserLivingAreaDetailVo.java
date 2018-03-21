package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:19
 * @description
 */
@ApiModel(value = "用户生活区域详情")
public class UserLivingAreaDetailVo implements ResponseData {

    @ApiModelProperty(value = "活跃地区")
    private String address;

    @ApiModelProperty(value = "活跃时间")
    private Date createdDate;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
