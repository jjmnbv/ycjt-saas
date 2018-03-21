package com.beitu.saas.common.utils.excel.model;

import java.util.LinkedHashMap;

/**
 * 导出 通讯录信息 excel文件
 * 表头内容 设计
 */
public class ExportContactInfo {

    private String name;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static LinkedHashMap<String, String> getHeader(){
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("name", "通讯录备注");
        header.put("phone", "通讯方式");
        return header;
    }

}
