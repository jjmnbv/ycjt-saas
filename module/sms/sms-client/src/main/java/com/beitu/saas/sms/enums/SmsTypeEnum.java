/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
     * 短信类型枚举
 *
 * @name 短信类型枚举
 * @author liting
 * @version $Id: SmsTypeEnum.java, v 0.1 2017年10月31日 下午3:13:46 liting Exp $
 */
public enum SmsTypeEnum {

    VERIFY_CODE(1,"验证码短信"),
    BIZ(2,"业务短信"),
    NOTIFY(3,"通知短信"),
    SALES(4,"营销短信");
    /**
     * 类型
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    private SmsTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     *将该枚举全部转化成json
     * @return
     */
    public static String toJson(){
        JSONArray jsonArray = new JSONArray();
        for (SmsTypeEnum e : SmsTypeEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", e.getCode());
            object.put("desc", e.getDesc());
            jsonArray.add(object);
        }
        return jsonArray.toString();
    }

    public static String getMessageByCode(Integer code){
        SmsTypeEnum[] smsTypeEnums = SmsTypeEnum.values();
        for (SmsTypeEnum smsTypeEnum : smsTypeEnums){
            if (smsTypeEnum.getCode().equals(code)){
                return smsTypeEnum.getDesc();
            }
        }
        return null;
    }
}
