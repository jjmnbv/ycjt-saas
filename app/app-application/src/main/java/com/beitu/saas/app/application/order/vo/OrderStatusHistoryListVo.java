package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:16
 * @description
 */
@ApiModel(value = "操作记录信息")
public class OrderStatusHistoryListVo implements ResponseData {

    @ApiModelProperty(value = "操作时间")
    private String operatorDate;

    @ApiModelProperty(value = "操作人")
    private String operatorName;

    @ApiModelProperty(value = "操作内容")
    private String operatorContent;

    public String getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(String operatorDate) {
        this.operatorDate = operatorDate;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorContent() {
        return operatorContent;
    }

    public void setOperatorContent(String operatorContent) {
        this.operatorContent = operatorContent;
    }
}
