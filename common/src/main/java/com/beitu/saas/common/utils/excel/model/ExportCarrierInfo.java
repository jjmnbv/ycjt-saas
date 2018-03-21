package com.beitu.saas.common.utils.excel.model;

import java.util.LinkedHashMap;

/**
 * 导出 运营商信息 excel文件
 * 表头内容 设计
 */
public class ExportCarrierInfo {

    private String mobile; // 手机号码

    private String callingDate; // 通话时间

    private String callTime; // 时长（秒）

    private String dialtype; // 呼叫类型（主叫和被叫）

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCallingDate() {
        return callingDate;
    }

    public void setCallingDate(String callingDate) {
        this.callingDate = callingDate;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getDialtype() {
        return dialtype;
    }

    public void setDialtype(String dialtype) {
        this.dialtype = dialtype;
    }

    public static LinkedHashMap getHeader(){
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("mobile", "手机号码");
        header.put("callingDate", "通话时间");
        header.put("callTime", "时长（秒）");
        header.put("dialtype", "呼叫类型");
        return header;
    }

}
