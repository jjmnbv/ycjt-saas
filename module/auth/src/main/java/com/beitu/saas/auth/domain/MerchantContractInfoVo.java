package com.beitu.saas.auth.domain;

/**
 * @author xiaochong
 * @create 2018/4/2 下午5:52
 * @description
 */
public class MerchantContractInfoVo {

    private Integer contractType;

    private String name;

    private String code;

    private String contractUrl;

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }
}
