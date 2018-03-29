package com.beitu.saas.intergration.esign;

import com.beitu.saas.intergration.esign.param.OrganizeAccountParam;
import com.beitu.saas.intergration.esign.param.PersonAccountParam;

import java.io.InputStream;

/**
 * @author linanjun
 * @create 2018/3/29 下午3:54
 * @description
 */
public interface EsignIntegrationService {

    Boolean addPersonAccount(PersonAccountParam personAccountParam);

    Boolean addOrganizeAccount(OrganizeAccountParam organizeAccountParam);

    InputStream doContractSign(String merchantCode, String borrowerCode, String srcPdf);

}