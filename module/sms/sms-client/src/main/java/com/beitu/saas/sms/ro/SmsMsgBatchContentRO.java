package com.beitu.saas.sms.ro;

import java.io.Serializable;
import java.util.Map;

public class SmsMsgBatchContentRO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1330833574367650679L;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 内容
     */
    private Map<String,String> replaceParam;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<String, String> getReplaceParam() {
        return replaceParam;
    }

    public void setReplaceParam(Map<String, String> replaceParam) {
        this.replaceParam = replaceParam;
    }
}
