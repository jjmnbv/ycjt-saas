package com.beitu.saas.channel.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午2:22
 */
public enum RiskModuleEnum {
    APPLICATION(101, "申请表"),
    PERSON_INFO(102, "个人信息"),
    EMERGENT_CONTACT(103, "紧急联系人"),
    WORK_INFO(104, "工作信息"),
    CARRIER_AUTHENTIC(105, "运营商认证"),
    ZM_CREDIT(106, "芝麻信用"),
    ONLINE_AUTHENTIC(107, "电商认证"),
    ROOF_BORROW_CREDIT(108, "多平台借贷信用"),
    IDENTITY_CARD(109, "身份证");

    private Integer moduleCode;

    private String desc;

    RiskModuleEnum(Integer moduleCode, String desc) {
        this.moduleCode = moduleCode;
        this.desc = desc;
    }

    public Integer getModuleCode() {
        return moduleCode;
    }

    public RiskModuleEnum setModuleCode(Integer moduleCode) {
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

    public static RiskModuleEnum getRiskModuleEnumByModuleCode(Integer moduleCode) {
        RiskModuleEnum moduleEnum = null;
        for (RiskModuleEnum e : RiskModuleEnum.values()) {
            if (e.getModuleCode().equals(moduleCode)) {
                moduleEnum = e;
            }
        }
        return moduleEnum;
    }
}
