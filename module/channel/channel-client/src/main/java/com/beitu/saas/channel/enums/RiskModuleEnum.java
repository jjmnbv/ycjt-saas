package com.beitu.saas.channel.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午2:22
 */
public enum RiskModuleEnum {

    APPLICATION("101", "申请表"),
    PERSONAL_INFO("102", "个人信息"),
    IDENTITY_INFO("103", "身份证"),
    WORK_INFO("104", "工作信息"),
    EMERGENT_CONTACT("105", "紧急联系人"),
    ZM_CREDIT("106", "芝麻信用"),
    CARRIER_AUTHENTIC("107", "运营商认证"),
    EB_INFO("108", "电商认证"),
    PLATFORM_BORROW_CREDIT("109", "多平台借贷信用");

    private String moduleCode;

    private String desc;

    RiskModuleEnum(String moduleCode, String desc) {
        this.moduleCode = moduleCode;
        this.desc = desc;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public RiskModuleEnum setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public RiskModuleEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public static RiskModuleEnum getRiskModuleEnumByModuleCode(String moduleCode) {
        RiskModuleEnum moduleEnum = null;
        for (RiskModuleEnum e : RiskModuleEnum.values()) {
            if (e.getModuleCode().equals(moduleCode)) {
                moduleEnum = e;
            }
        }
        return moduleEnum;
    }
}
