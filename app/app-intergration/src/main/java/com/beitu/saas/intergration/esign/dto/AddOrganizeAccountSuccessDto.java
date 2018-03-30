package com.beitu.saas.intergration.esign.dto;

/**
 * @author linanjun
 * @create 2018/3/30 下午3:19
 * @description
 */
public class AddOrganizeAccountSuccessDto {

    private String organizeAccountId;

    private String sealData;

    public AddOrganizeAccountSuccessDto(String organizeAccountId, String sealData) {
        this.organizeAccountId = organizeAccountId;
        this.sealData = sealData;
    }

    public String getOrganizeAccountId() {
        return organizeAccountId;
    }

    public void setOrganizeAccountId(String organizeAccountId) {
        this.organizeAccountId = organizeAccountId;
    }

    public String getSealData() {
        return sealData;
    }

    public void setSealData(String sealData) {
        this.sealData = sealData;
    }

}
