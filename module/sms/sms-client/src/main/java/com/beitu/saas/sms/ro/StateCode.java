package com.beitu.saas.sms.ro;

public enum StateCode {

    SUCCESS(0,"成功"),
    FAIL(-110,"操作失败"),
    PARAMETER_LACK(-1101,"参数缺失"),
    ILLEGAL_PARAMS(-1102,"参数异常"),
    MESSAGE_TEMPLATE_LACK(-1201,"该功能对应的消息模板不存在"),
    MESSAGE_TEMPLATE_SERVICER_LACK(-1202,"该功能未配置服务商"),
    UNKOWN_SERVICER(-1203,"未知服务商信息"),
    DINGTALK_ERROR(-1301,"钉钉消息异常"),
    QUEUE_OOM(-1401,"数据队列已满");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息内容
     */
    private String message;

    StateCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Boolean isSuccess(){
       return this.getCode()>=0;
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
}
