package com.beitu.saas.intergration.risk.pojo;

import java.util.List;

public class LoanPlatformLendPojo {
    
    /**
     * 总借出金额
     */
    private String total;
    
    /**
     * 总借款利息
     */
    private String totalInterest;
    
    /**
     * 出借次数
     */
    private String times;
    
    /**
     * 出借列表
     */
    private List<LoanPlatformBorrowedLendDetailPojo> lendDetails;
    
    public String getTotal() {
        return total;
    }
    
    public String getTotalInterest() {
        return totalInterest;
    }
    
    public String getTimes() {
        return times;
    }
    
    public List<LoanPlatformBorrowedLendDetailPojo> getLendDetails() {
        return lendDetails;
    }
}
