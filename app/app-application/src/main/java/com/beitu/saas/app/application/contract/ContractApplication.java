package com.beitu.saas.app.application.contract;

import com.beitu.saas.intergration.esign.EsignIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author linanjun
 * @Create 2017/10/11 下午8:19
 * @Description
 */
@Component
public class ContractApplication {

    @Autowired
    private EsignIntegrationService esignIntegrationService;

    public Boolean needDoLicenseContractSign(String userCode) {
        return Boolean.TRUE;
    }

    public void doLicenseContractSign(String userCode) {

    }

    public void borrowerDoLoanContractSign(String borrowerCode, String orderNumb) {

    }

    public void lenderDoLoanContractSign(String merchantCode, String orderNumb) {

    }

    public void doExtendContractSign(String merchantCode, String borrowerCode, String orderNumb) {

    }

}
