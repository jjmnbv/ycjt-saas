package com.beitu.saas.sms.ro;

import java.io.Serializable;
import java.util.Map;

public class Result<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7666935509876463461L;
	/**
     * 返回结果
     */
    private T data;
    /**
     * 状态码
     */
    private StateCode stateCode;
    /**
     * 状态描述信息
     */
    private String statusText;
    /**
     * 拓展信息
     */
    private Map<String, String> extData;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Map<String, String> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, String> extData) {
        this.extData = extData;
    }

    public Boolean isSuccess(){
        return this.stateCode.getCode() >= 0;
    }

}
