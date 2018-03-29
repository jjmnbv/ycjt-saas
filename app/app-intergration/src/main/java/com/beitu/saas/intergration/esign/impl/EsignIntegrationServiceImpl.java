package com.beitu.saas.intergration.esign.impl;

import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.intergration.esign.param.OrganizeAccountParam;
import com.beitu.saas.intergration.esign.param.PersonAccountParam;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author linanjun
 * @create 2018/3/29 下午5:21
 * @description
 */
@Service
public class EsignIntegrationServiceImpl implements EsignIntegrationService {

    @Override
    public Boolean addPersonAccount(PersonAccountParam personAccountParam) {
        return null;
    }

    @Override
    public Boolean addOrganizeAccount(OrganizeAccountParam organizeAccountParam) {
        return null;
    }

    @Override
    public InputStream doContractSign(String merchantCode, String borrowerCode, String srcPdf) {
        return null;
    }
}
