package com.beitu.saas.rest.controller.order.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

/**
 * @author linanjun
 * @create 2018/3/21 下午3:59
 * @description
 */
@ApiModel(value = "贷后管理订单详情")
public class AfterLendOrderDetailResponse implements ResponseData {

    private String orderNumb;

    private String contractTitle;

    private String contractUrl;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }
}
