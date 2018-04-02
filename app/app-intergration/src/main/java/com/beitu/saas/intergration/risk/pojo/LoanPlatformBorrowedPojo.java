package com.beitu.saas.intergration.risk.pojo;

import java.util.List;

public class LoanPlatformBorrowedPojo {
    
    /**
     * 总借入金额
     */
    private String total;
    
    /**
     * 总借款利息
     */
    private String totalInterest;
    
    /**
     * 借款次数
     */
    private String times;
    
    /**
     * 借入列表
     */
    private List<LoanPlatformBorrowedLendDetailPojo> borrowedDetails;
    
    public String getTotal() {
        return total;
    }
    
    public String getTotalInterest() {
        return totalInterest;
    }
    
    public String getTimes() {
        return times;
    }
    
    public List<LoanPlatformBorrowedLendDetailPojo> getBorrowedDetails() {
        return borrowedDetails;
    }
}
