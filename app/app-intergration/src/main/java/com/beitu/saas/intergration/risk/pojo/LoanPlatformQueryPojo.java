package com.beitu.saas.intergration.risk.pojo;

public class LoanPlatformQueryPojo {
    
    /**
     * 查询状态 true 成功 false 失败
     */
    private Boolean success;
    
    /**
     * 查询状态码
     */
    private String code;
    
    /**
     * 查询结果信息
     */
    private String msg;
    
    /**
     * 具体信息
     */
    private LoanPlatformQueryDetailPojo detail;
    
    public Boolean getSuccess() {
        return success;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public LoanPlatformQueryDetailPojo getDetail() {
        return detail;
    }
}
