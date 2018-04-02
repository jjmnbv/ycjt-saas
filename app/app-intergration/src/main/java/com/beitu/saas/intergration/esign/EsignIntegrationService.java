package com.beitu.saas.intergration.esign;

import com.beitu.saas.intergration.esign.dto.AddOrganizeAccountSuccessDto;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.*;

import java.io.InputStream;

/**
 * @author linanjun
 * @create 2018/3/29 下午3:54
 * @description
 */
public interface EsignIntegrationService {

    /**
     * 新增 e签宝 个人账户
     *
     * @param personAccountParam
     * @return
     */
    AddPersonAccountSuccessDto addPersonAccount(PersonAccountParam personAccountParam);

    /**
     * 新增 e签宝 机构账户
     *
     * @param organizeAccountParam
     * @return
     */
    AddOrganizeAccountSuccessDto addOrganizeAccount(OrganizeAccountParam organizeAccountParam);

    /**
     * 借款人 签署 协议
     *
     * @param borrowerDoContractSignParam
     * @return
     */
    String borrowerDoContractSign(BorrowerDoContractSignParam borrowerDoContractSignParam);

    /**
     * 机构 签署 协议
     *
     * @param lenderDoContractSignParam
     * @return
     */
    String lenderDoContractSign(LenderDoContractSignParam lenderDoContractSignParam);

    /**
     * 签署 授权协议
     *
     * @param licenseContractSignParam
     * @return
     */
    String doLicenseContractSign(LicenseContractSignParam licenseContractSignParam);

}