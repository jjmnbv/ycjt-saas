package com.beitu.saas.app.application.contract;

import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.user.client.SaasUserEsignAuthorizationService;
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

    @Autowired
    private SaasUserEsignAuthorizationService saasUserEsignAuthorizationService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasMerchantService saasMerchantService;

    public Boolean needDoLicenseContractSign(String userCode) {
        if (saasUserEsignAuthorizationService.isSuccessAuthorization(userCode)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String getUserSealUrl(String userCode) {
        return saasUserEsignAuthorizationService.getSealUrlByUserCode(userCode);
    }

    /**
     * 签署 esign 授权协议
     *
     * @param userCode 借款人传 borrowerCode；机构传 merchantCode
     */
    public void doLicenseContractSign(String userCode) {
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(userCode);
        if (saasBorrowerRealInfoVo != null) {

        }
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(userCode);
        if (saasMerchantVo != null) {

        }
    }

    public void borrowerDoLoanContractSign(String borrowerCode, Long orderId) {

    }

    public void lenderDoLoanContractSign(String merchantCode, Long orderId) {

    }

    public void borrowerDoExtendContractSign(String borrowerCode, Long orderId) {

    }

    public void lenderDoExtendContractSign(String borrowerCode, Long orderId) {

    }

}
