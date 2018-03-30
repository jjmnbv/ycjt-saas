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

    AddPersonAccountSuccessDto addPersonAccount(PersonAccountParam personAccountParam);

    AddOrganizeAccountSuccessDto addOrganizeAccount(OrganizeAccountParam organizeAccountParam);

    InputStream doLoanContractSign(LoanContractSignParam loanContractSignParam);

    InputStream doLicenseContractSign(LicenseContractSignParam licenseContractSignParam);

}