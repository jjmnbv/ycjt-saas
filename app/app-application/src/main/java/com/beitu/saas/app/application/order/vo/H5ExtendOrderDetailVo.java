package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;

/**
 * @author linanjun
 * @create 2018/3/25 下午2:33
 * @description
 */

public class H5ExtendOrderDetailVo implements ResponseData {

    private Integer extendDuration = 7;

    private String repaymentDt = "2018-09-08";

    private String totalInterestRatio = "24%";

    public Integer getExtendDuration() {
        return extendDuration;
    }

    public void setExtendDuration(Integer extendDuration) {
        this.extendDuration = extendDuration;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

}
