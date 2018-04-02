package com.beitu.saas.intergration.esign.dto;

/**
 * @author linanjun
 * @create 2018/3/30 下午2:32
 * @description
 */
public class AddPersonAccountSuccessDto {

    private String personAccountId;

    private String sealData;

    public AddPersonAccountSuccessDto(String personAccountId, String sealData) {
        this.personAccountId = personAccountId;
        this.sealData = sealData;
    }

    public String getPersonAccountId() {
        return personAccountId;
    }

    public void setPersonAccountId(String personAccountId) {
        this.personAccountId = personAccountId;
    }

    public String getSealData() {
        return sealData;
    }

    public void setSealData(String sealData) {
        this.sealData = sealData;
    }
}
