package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 上午11:11
 */
public class DashboardOverdueOrderShowVo implements ResponseData {
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "账单金额")
    private BigDecimal realCapital;

    public String getName() {
        return name;
    }

    public DashboardOverdueOrderShowVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public DashboardOverdueOrderShowVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public DashboardOverdueOrderShowVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }
}
