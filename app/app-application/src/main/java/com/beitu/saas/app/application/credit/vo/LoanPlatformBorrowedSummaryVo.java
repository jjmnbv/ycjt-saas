package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "借贷平台借入汇总信息")
public class LoanPlatformBorrowedSummaryVo implements ResponseData {
    
    @ApiModelProperty(value = "借入总金额")
    private String total;
    
    @ApiModelProperty(value = "借入次数")
    private String times;
    
    @ApiModelProperty(value = "已还清总额")
    private String repaidTotal;
    
    @ApiModelProperty(value = "逾期已还总额")
    private String overdueRepaidTotal;
    
    @ApiModelProperty(value = "未还清总额")
    private String notRepaidTotal;
    
    @ApiModelProperty(value = "已逾期总额")
    private String overdueTotal;
    
    public String getTotal() {
        return total;
    }
    
    public void setTotal(String total) {
        this.total = total;
    }
    
    public String getTimes() {
        return times;
    }
    
    public void setTimes(String times) {
        this.times = times;
    }
    
    public String getRepaidTotal() {
        return repaidTotal;
    }
    
    public void setRepaidTotal(String repaidTotal) {
        this.repaidTotal = repaidTotal;
    }
    
    public String getOverdueRepaidTotal() {
        return overdueRepaidTotal;
    }
    
    public void setOverdueRepaidTotal(String overdueRepaidTotal) {
        this.overdueRepaidTotal = overdueRepaidTotal;
    }
    
    public String getNotRepaidTotal() {
        return notRepaidTotal;
    }
    
    public void setNotRepaidTotal(String notRepaidTotal) {
        this.notRepaidTotal = notRepaidTotal;
    }
    
    public String getOverdueTotal() {
        return overdueTotal;
    }
    
    public void setOverdueTotal(String overdueTotal) {
        this.overdueTotal = overdueTotal;
    }
}
