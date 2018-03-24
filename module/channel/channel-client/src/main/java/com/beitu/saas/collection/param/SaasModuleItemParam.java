package com.beitu.saas.collection.param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午4:43
 */
public class SaasModuleItemParam {
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

    public SaasModuleItemParam setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getItemCode() {
        return itemCode;
    }

    public SaasModuleItemParam setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public SaasModuleItemParam setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }
}
