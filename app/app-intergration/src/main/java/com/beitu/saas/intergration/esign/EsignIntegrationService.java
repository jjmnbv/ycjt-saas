package com.beitu.saas.intergration.esign;

import com.beitu.saas.intergration.esign.dto.AddOrganizeAccountSuccessDto;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.LicenseContractSignParam;
import com.beitu.saas.intergration.esign.param.LoanContractSignParam;
import com.beitu.saas.intergration.esign.param.OrganizeAccountParam;
import com.beitu.saas.intergration.esign.param.PersonAccountParam;

import java.io.InputStream;

/**
 * @author linanjun
 * @create 2018/3/29 下午3:54
 * @description
 */
public interface EsignIntegrationService {

    /**
     * 新增 e签宝 个人账户
     * @param personAccountParam
     * @return
     */
    AddPersonAccountSuccessDto addPersonAccount(PersonAccountParam personAccountParam);

    /**
     * 新增 e签宝 机构账户
     * @param organizeAccountParam
     * @return
     */
    AddOrganizeAccountSuccessDto addOrganizeAccount(OrganizeAccountParam organizeAccountParam);

    /**
     * 签署 借款协议
     * @param loanContractSignParam
     * @return
     */
    InputStream doLoanContractSign(LoanContractSignParam loanContractSignParam);

    /**
     * 签署 授权协议
     * @param licenseContractSignParam
     * @return
     */
    InputStream doLicenseContractSign(LicenseContractSignParam licenseContractSignParam);

}