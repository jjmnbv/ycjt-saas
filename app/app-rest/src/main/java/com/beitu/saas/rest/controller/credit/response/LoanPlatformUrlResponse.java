package com.beitu.saas.rest.controller.credit.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

/**
 * @author linanjun
 * @create 2018/3/30 下午5:02
 * @description
 */
@ApiModel(value = "多平台借贷爬取URL返回数据")
public class LoanPlatformUrlResponse implements ResponseData {

    private String jumpUrl;

    public LoanPlatformUrlResponse(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

}
