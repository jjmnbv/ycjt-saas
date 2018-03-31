package com.beitu.saas.app.application.contract;

import org.springframework.stereotype.Component;

/**
 * @Author linanjun
 * @Create 2017/10/11 下午8:19
 * @Description
 */
@Component
public class ContractApplication {


    public Boolean needDoLicenseContractSign(String userCode) {
        return Boolean.TRUE;
    }

    public void doLicenseContractSign(String userCode) {

    }

    public void doLoanContractSign(String merchantCode, String borrowerCode, String orderId) {

    }

    public void doExtendContractSign(String merchantCode, String borrowerCode, String orderId) {

    }

}
