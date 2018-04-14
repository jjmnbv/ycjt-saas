package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.intergration.risk.pojo.LoanPlatformBorrowedLendDetailPojo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "借贷平台借入详情信息")
public class LoanPlatformBorrowedDetailVo implements ResponseData {
    
    @ApiModelProperty(value = "姓名")
    private String userName;
    
    @ApiModelProperty(value = "借款类型")
    private String type;
    
    @ApiModelProperty(value = "金额")
    private String amount;
    
    @ApiModelProperty(value = "状态码")
    private String statusCode;
    
    @ApiModelProperty(value = "状态描述")
    private String statusDes;
    
    @ApiModelProperty(value = "出借时间")
    private String loanTime;
    
    @ApiModelProperty(value = "还款时间")
    private String repaymentTime;
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getStatusDes() {
        return statusDes;
    }
    
    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }
    
    public String getLoanTime() {
        return loanTime;
    }
    
    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }
    
    public String getRepaymentTime() {
        return repaymentTime;
    }
    
    public void setRepaymentTime(String repaymentTime) {
        this.repaymentTime = repaymentTime;
    }
    
    public static LoanPlatformBorrowedDetailVo convertPojoToVo(LoanPlatformBorrowedLendDetailPojo pojo) {
        if (pojo == null) {
            return null;
        }
        LoanPlatformBorrowedDetailVo vo = new LoanPlatformBorrowedDetailVo();
        BeanUtils.copyProperties(pojo, vo);
        return vo;
    }
}
