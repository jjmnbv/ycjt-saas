package com.beitu.saas.intergration.user.pojo;

public class UserNameIdNoValidationPojo {
    
    /**
     * 提交响应码
     */
    private String resCode;
    
    /**
     * 提交响应码中文描述
     */
    private String resMsg;
    
    /**
     * 业务级参数
     */
    private UserNameIdNoValidationDataPojo data;
    
    public String getResCode() {
        return resCode;
    }
    
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    
    public String getResMsg() {
        return resMsg;
    }
    
    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
    
    public UserNameIdNoValidationDataPojo getData() {
        return data;
    }
    
    public void setData(UserNameIdNoValidationDataPojo data) {
        this.data = data;
    }
}
