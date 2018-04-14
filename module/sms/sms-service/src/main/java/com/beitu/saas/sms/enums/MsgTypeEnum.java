package com.beitu.saas.sms.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public enum MsgTypeEnum {

    TEXT_MSG(1, "短信"),
    VOICE_MSG(2, "语音"),
    EMAIL(3, "邮件"),
    APP_PUSH(4, "站内push");

    private Integer code;
    private String message;

    MsgTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 将该枚举全部转化成json
     *
     * @return
     */
    public static String toJson() {
        JSONArray jsonArray = new JSONArray();
        for (MsgTypeEnum e : MsgTypeEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", e.getCode());
            object.put("message", e.getMessage());
            jsonArray.add(object);
        }
        return jsonArray.toString();
    }

    public static String getMessageByCode(Integer code) {
        MsgTypeEnum[] msgTypeEnums = MsgTypeEnum.values();
        for (MsgTypeEnum msgTypeEnum : msgTypeEnums) {
            if (msgTypeEnum.getCode() == code) {
                return msgTypeEnum.getMessage();
            }
        }
        return null;
    }

}
