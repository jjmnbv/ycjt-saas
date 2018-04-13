package com.beitu.saas.app.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/13
 * Time: 下午2:43
 */
public enum SaasConsumeMsgTypeEnum {
    CREDIT(1, "点券"),
    MESSAGE(3, "短信");

    private Integer type;
    private String msg;

    SaasConsumeMsgTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public SaasConsumeMsgTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public SaasConsumeMsgTypeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static String getMsgByType(Integer type) {
        for (SaasConsumeMsgTypeEnum msgTypeEnum : SaasConsumeMsgTypeEnum.values()) {
            if (type == msgTypeEnum.getType()) {
                return msgTypeEnum.getMsg();
            }
        }
        return "";
    }
}
