/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.enums;

/**
 * app enum
 *
 * @name  app enum
 * @author liting
 * @version $Id: AppEnum.java, v 0.1 2017年9月14日 上午11:37:37 liting Exp $
 */
public enum AppEnum {
    QSYQ(1,"QSYQ","轻松有钱"),
    SDZZ(2,"SDZZ","闪电周转"),
    XJDR(3,"XJDR","现金大人"),
    YCJT(4,"YCJT","洋葱借条"),
    JYD(5,"JYD","金壹贷"),
    JKZJ(7,"JKZJ","借款专家");

//    FQGJ(1,"FQGJ","分期管家"),
//    SDZZ(2,"SDZZ","闪电周转"),
//    QSYQ(3,"QSYQ","轻松有钱"),
//    XJDR(4,"XJDR","现金大人"),
//    JYD(5,"JYD","金壹贷"),
//    YCJT(6,"YCJT","洋葱借条"),
//    JKZJ(7,"JKZJ","借款专家"),
//    JKZJ_VOICE(8, "JKZJ", "借款专家语音验证码"),
//    QLZD(9, "QLZD", "钱粒账单"),
//    QLZD_VOICE(10, "QLZD", "钱粒账单语音验证码");

    private AppEnum(Integer id, String code, String msg) {
        this.id = id;
        this.code = code;
        this.msg = msg;
    }
    /**
     * app id
     */
    private Integer id;
    /**
     * app code
     */
    private String code;
    /**
     * app msg
     */
    private String msg;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getAppNameById(Integer id){
        AppEnum[] appEnumps = AppEnum.values();
        for (AppEnum appEnum : appEnumps){
            if (appEnum.getId() == id){
                return appEnum.getMsg();
            }
        }
        return null;
    }
}
