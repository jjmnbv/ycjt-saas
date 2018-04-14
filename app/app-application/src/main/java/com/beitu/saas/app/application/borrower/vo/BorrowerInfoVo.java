package com.beitu.saas.app.application.borrower.vo;

/**
 * @author linanjun
 * @create 2018/3/28 下午1:58
 * @description
 */
public class BorrowerInfoVo {

    private String borrowerCode;

    private String borrowerName;

    private String borrowerMobile;

    private String borrowerGender;

    private Integer borrowerAge;

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerMobile() {
        return borrowerMobile;
    }

    public void setBorrowerMobile(String borrowerMobile) {
        this.borrowerMobile = borrowerMobile;
    }

    public String getBorrowerGender() {
        return borrowerGender;
    }

    public void setBorrowerGender(String borrowerGender) {
        this.borrowerGender = borrowerGender;
    }

    public Integer getBorrowerAge() {
        return borrowerAge;
    }

    public void setBorrowerAge(Integer borrowerAge) {
        this.borrowerAge = borrowerAge;
    }

}
