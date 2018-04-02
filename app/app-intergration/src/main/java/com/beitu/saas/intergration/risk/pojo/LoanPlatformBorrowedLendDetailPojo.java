package com.beitu.saas.intergration.risk.pojo;

public class LoanPlatformBorrowedLendDetailPojo {
    
    /**
     * 借款关系人姓名
     */
    private String userName;
    
    /**
     * 借款关系人身份证号
     */
    private String idCardNum;
    
    /**
     * 借款关系人电话
     */
    private String tel;
    
    /**
     * 借款金额
     */
    private String amount;
    
    /**
     * 借款利息
     */
    private String interest;
    
    /**
     * 借款类型
     */
    private String type;
    
    /**
     * 借款状态码
     */
    private String statusCode;
    
    /**
     * 借款状态描述
     */
    private String statusDes;
    
    /**
     * 借款时间
     */
    private String loanTime;
    
    /**
     * 约定还款时间
     */
    private String repaymentTime;
    
    /**
     * 实际还款时间
     */
    private String payBackTime;
    
    public String getUserName() {
        return userName;
    }
    
    public String getIdCardNum() {
        return idCardNum;
    }
    
    public String getTel() {
        return tel;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public String getInterest() {
        return interest;
    }
    
    public String getType() {
        return type;
    }
    
    public String getStatusCode() {
        return statusCode;
    }
    
    public String getStatusDes() {
        return statusDes;
    }
    
    public String getLoanTime() {
        return loanTime;
    }
    
    public String getRepaymentTime() {
        return repaymentTime;
    }
    
    public String getPayBackTime() {
        return payBackTime;
    }
}
