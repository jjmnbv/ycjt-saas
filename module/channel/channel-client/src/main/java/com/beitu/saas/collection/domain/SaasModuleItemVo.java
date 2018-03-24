package com.beitu.saas.collection.domain;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午4:43
 */
public class SaasModuleItemVo {
    /**
     *模块号
     */
    private String moduleCode;
    /**
     *字段号
     */
    private String itemCode;
    /**
     *字段名称
     */
    private String itemDesc;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasModuleItemVo setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getItemCode() {
        return itemCode;
    }

    public SaasModuleItemVo setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public SaasModuleItemVo setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }
}
